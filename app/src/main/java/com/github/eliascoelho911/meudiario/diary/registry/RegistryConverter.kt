package com.github.eliascoelho911.meudiario.diary.registry

import android.content.Context
import androidx.annotation.DrawableRes
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.data.registry.Mood
import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import java.time.format.DateTimeFormatter

class RegistryConverter(private val context: Context) {
    private val hourFormatter by lazy { DateTimeFormatter.ofPattern("hh:MM") }

    fun convert(dto: RegistryDTO): RegistryVO {
        val hour = dto.dateTime.format(hourFormatter)
        return RegistryVO(hour,
            dto.title,
            dto.body,
            getMoodDescription(dto.mood),
            getMoodIcon(dto.mood))
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