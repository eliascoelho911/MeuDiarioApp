package com.github.eliascoelho911.meudiario.diary.registry

import androidx.annotation.DrawableRes

data class RegistryVO(
    val time: String = "",
    val title: String = "",
    val body: String = "",
    val moodDescription: String = "",
    @DrawableRes val moodRes: Int = 0,
)
