package room.dao.entity

import data.enums.Language
import room.dbo.entity.Definition
import room.dbo.entity.Vocabulary
import kotlinx.coroutines.runBlocking
import org.junit.Test
import room.dao.AbstractDaoTest
import kotlin.test.assertEquals

class DefinitionDaoTest : AbstractDaoTest() {
    private val definitionText = "Test"
    private val dictionary = data.enums.Dictionary.SANSEIDO
    private val language = Language.JAPANESE
    private var definitionID : Long = 0
    private fun insertTestData(){
        val vocabularyID = runBlocking {
            db.vocabularyDao().insert(Vocabulary("", "", "", Language.JAPANESE))
        }

        val definition = Definition(definitionText, language, dictionary, vocabularyID)

        definitionID = runBlocking {
            db.definitionDao().insert(definition)
        }
    }

    @Test
    fun getDefinitionID_Exists_ReturnsID() {
        insertTestData()
        val daoDefinitionID = db.definitionDao().getDefinitionID(definitionText, dictionary, language)
        assertEquals(definitionID, daoDefinitionID)
    }

    @Test
    fun getDefinitionID_DefinitionTextMismatch_ReturnsNull() {
        insertTestData()
        val daoDefinitionID = db.definitionDao().getDefinitionID("", dictionary, language)
        assertEquals(null, daoDefinitionID)
    }

    @Test
    fun getDefinitionID_LanguageMismatch_ReturnsNull() {
        insertTestData()
        val daoDefinitionID = db.definitionDao().getDefinitionID("", dictionary, Language.ENGLISH)
        assertEquals(null, daoDefinitionID)
    }
}