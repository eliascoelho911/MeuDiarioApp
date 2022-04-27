package com.github.eliascoelho911.meudiario.domain.entities

import java.time.LocalDateTime

data class Registry(
    val id: Long,
    val dateTime: LocalDateTime,
    val title: String,
    val body: String,
    val mood: Mood,
)
