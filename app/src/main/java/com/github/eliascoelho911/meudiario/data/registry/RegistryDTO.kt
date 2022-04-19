package com.github.eliascoelho911.meudiario.data.registry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "registry")
data class RegistryDTO(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date_time") val dateTime: LocalDateTime,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "mood") val mood: Mood,
)

enum class Mood {
    VERY_HAPPY, HAPPY, NEUTRAL, SAD, VERY_SAD
}
