package data.room.dao

import androidx.room.Dao
import androidx.room.Query
import data.room.entity.MatchType

@Dao
interface MatchTypeDao : BaseDao<MatchType> {
    @Query("""
        SELECT mt.TemplateString
        FROM MatchType mt
        WHERE mt.MatchTypeID = :bitmask """)
    fun getTemplateString(bitmask : Long): String

    @Query( """
        SELECT mt.*
        FROM MatchType mt""")
    fun getAllMatchTypes() : List<MatchType>

    @Query("""
        SELECT mt.*
        FROM MatchType mt
        JOIN DictionaryMatchType dmt
            ON dmt.MatchTypeID = mt.MatchTypeID
        WHERE dmt.DictionaryID = :dictionaryID
    """)
    suspend fun getDictionaryMatchTypes(dictionaryID: Long) : List<MatchType>
}