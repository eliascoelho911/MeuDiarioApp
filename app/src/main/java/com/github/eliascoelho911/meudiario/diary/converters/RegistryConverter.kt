package com.github.eliascoelho911.meudiario.diary.converters

import android.content.Context
import androidx.annotation.DrawableRes
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.data.registry.Mood
import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import com.github.eliascoelho911.meudiario.diary.vo.RegistryVO
import java.time.format.DateTimeFormatter

class RegistryConverter(private val context: Context) {
    private val hourFormatter by lazy { DateTimeFormatter.ofPattern("HH:mm") }

    fun convert(data: List<RegistryDTO>): List<RegistryVO> =
        data.map {
            val hour = it.dateTime.format(hourFormatter)
            RegistryVO(it.id,
                it.dateTime,
                hour,
                it.title,
                it.body,
                getMoodDescription(it.mood),
                getMoodIcon(it.mood))
        }

    private fun getMoodDescription(mood: Mood) = when (mood) {
        Mood.VERY_SAD -> context.getString(R.string.cd_mood_very_sad)
        Mood.SAD -> context.getString(R.string.cd_mood_sad)
        Mood.NEUTRAL -> context.getString(R.string.cd_mood_neutral)
        Mood.HAPPY -> context.getString(R.string.cd_mood_happy)
        Mood.VERY_HAPPY -> context.getString(R.string.cd_mood_very_happy)
    }

    @DrawableRes
    private fun getMoodIcon(mood: Mood) = when (mood) {
        Mood.VERY_SAD -> R.drawable.ic_crying_face_24
        Mood.SAD -> R.drawable.ic_slightly_frowning_face_24
        Mood.NEUTRAL -> R.drawable.ic_neutral_face_24
        Mood.HAPPY -> R.drawable.ic_smiling_face_24
        Mood.VERY_HAPPY -> R.drawable.ic_grinning_face_24
    }
}