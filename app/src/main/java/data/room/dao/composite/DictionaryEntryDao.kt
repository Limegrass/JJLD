package data.room.dao.composite

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import data.enums.Language
import data.room.dbo.composite.DictionaryEntry

@Dao
interface DictionaryEntryDao {
    @Transaction
    @Query("""
        SELECT *
        FROM Vocabulary v
        WHERE v.LanguageID = :vocabularyLanguage
            AND (v.Word LIKE :searchTerm
                OR v.Pronunciation LIKE :searchTerm)""")
    fun searchWordLike(searchTerm: String, vocabularyLanguage: Language) : List<DictionaryEntry>

    @Transaction
    @Query("""
        SELECT *
        FROM Vocabulary v
        WHERE v.LanguageID = :vocabularyLanguage
            AND (v.Word = :searchTerm
                OR v.Pronunciation = :searchTerm)""")
    fun searchWordEqual(searchTerm: String, vocabularyLanguage: Language) : List<DictionaryEntry>

    @Transaction
    @Query("""
        SELECT v.*
        FROM Vocabulary v
        JOIN Definition d
            ON v.VocabularyID = d.VocabularyID
        WHERE d.LanguageID = :definitionLanguage
            AND d.DefinitionText LIKE :searchTerm""")
    fun searchDefinitionLike(searchTerm: String, definitionLanguage : Language) : List<DictionaryEntry>

    @Transaction
    @Query("""
        SELECT v.*
        FROM Vocabulary v
        LEFT JOIN Definition d
            ON d.VocabularyID = v.VocabularyID
        WHERE v.LanguageID = :vocabularyLanguage
            AND d.LanguageID = :definitionLanguage
            AND (v.Word LIKE :searchTerm
                OR v.Pronunciation LIKE :searchTerm
                OR d.DefinitionText LIKE :searchTerm)""")
    fun searchWordOrDefinitionLike(searchTerm: String,
                                   vocabularyLanguage : Language,
                                   definitionLanguage: Language) : List<DictionaryEntry>
}