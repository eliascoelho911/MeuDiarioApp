package com.github.eliascoelho911.meudiario.presenter.converters

import com.github.eliascoelho911.meudiario.domain.entities.RegistryPerDay
import com.github.eliascoelho911.meudiario.presenter.diary.vo.RegistryPerDayVO
import java.time.format.TextStyle
import java.util.Locale

class RegistryPerDayConverter {
    fun convert(data: List<RegistryPerDay>): List<RegistryPerDayVO> = data.map {
        val day = it.date.dayOfMonth.toString()
        val month = it.date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        RegistryPerDayVO(
            day = day,
            month = month,
            date = it.date,
            registries = it.registries)
    }
}