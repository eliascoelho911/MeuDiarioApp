package com.github.eliascoelho911.meudiario.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.eliascoelho911.meudiario.domain.entities.Mood
import java.time.LocalDateTime

@Entity(tableName = "registry")
data class RegistryDTO(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "date_time") val dateTime: LocalDateTime,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "mood") val mood: Mood,
)
