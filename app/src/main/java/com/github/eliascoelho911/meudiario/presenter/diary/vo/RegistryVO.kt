package com.github.eliascoelho911.meudiario.presenter.diary.vo

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class RegistryVO(
    val id: Long = 0,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val timeFormatted: String = "",
    val title: String = "",
    val body: String = "",
    val moodDescription: String = "",
    @DrawableRes val moodRes: Int = 0,
)
