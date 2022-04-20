package com.github.eliascoelho911.meudiario.diary.registry.perday

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.common.TestCustomApplication
import com.github.eliascoelho911.meudiario.data.registry.Mood
import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import com.github.eliascoelho911.meudiario.diary.registry.RegistryConverter
import com.github.eliascoelho911.meudiario.diary.registry.RegistryVO
import java.time.LocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(application = TestCustomApplication::class)
@RunWith(AndroidJUnit4::class)
class GroupRegistriesByDayTest {
    private val context: Context by lazy {
        ApplicationProvider.getApplicationContext()
    }
    private val registryConverter by lazy {
        RegistryConverter(context)
    }
    private val groupRegistriesByDay by lazy { GroupRegistriesByDay(registryConverter) }
    private val time = LocalDateTime.of(2022, 4, 19, 22, 30)
    private val registries = listOf(RegistryDTO(0, time, "title1", "body1", Mood.VERY_HAPPY),
        RegistryDTO(1, time.plusMinutes(30), "title2", "body2", Mood.HAPPY),
        RegistryDTO(2, time.plusDays(1), "title3", "body3", Mood.NEUTRAL))

    @Test
    fun givenRegistries_whenSuccess_shouldReturnRegistryPerDay() {
        val result = groupRegistriesByDay.invoke(registries)
        val expected = listOf(RegistryPerDayVO("19",
            "apr",
            listOf(RegistryVO("22:30",
                "title1",
                "body1",
                "Se sentindo muito feliz",
                R.drawable.ic_grinning_face_24),
                RegistryVO("23:00",
                    "title2",
                    "body2",
                    "Se sentindo feliz",
                    R.drawable.ic_smiling_face_24))),
            RegistryPerDayVO("20", "apr", listOf(RegistryVO("22:30",
                "title3",
                "body3",
                "Se sentindo neutro",
                R.drawable.ic_neutral_face_24)
            )))

        assertEquals(expected, result)
    }
}