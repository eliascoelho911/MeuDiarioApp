package com.github.eliascoelho911.meudiario.diary.vo

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class RegistryVO(
    val id: Int = 0,
    val time: LocalDateTime = LocalDateTime.now(),
    val timeFormatted: String = "",
    val title: String = "",
    val body: String = "",
    val moodDescription: String = "",
    @DrawableRes val moodRes: Int = 0,
)
