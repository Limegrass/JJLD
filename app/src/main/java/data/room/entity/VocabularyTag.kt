package data.room.entity

import android.arch.persistence.room.*

@Entity(tableName = "VocabularyTag",
        foreignKeys = [
            ForeignKey(
                    entity = Vocabulary::class,
                    parentColumns = ["VocabularyID"],
                    childColumns = ["VocabularyID"])
        ]
)

data class VocabularyTag (
    @ColumnInfo(name = "TagID")
    var tagID: Int,

    @ColumnInfo(name = "VocabularyID")
    var vocabularyID: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "VocabularyTagID")
    var vocabularyTagID: Int = 0 )
