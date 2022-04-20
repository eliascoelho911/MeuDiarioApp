package com.github.eliascoelho911.meudiario.diary.registry

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.common.TestCustomApplication
import com.github.eliascoelho911.meudiario.data.registry.Mood
import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import java.time.LocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(application = TestCustomApplication::class)
@RunWith(AndroidJUnit4::class)
class RegistryConverterTest {
    private val context: Context by lazy {
        ApplicationProvider.getApplicationContext()
    }
    private val registryConverter by lazy {
        RegistryConverter(context)
    }
    private val time = LocalDateTime.of(2022, 4, 19, 22, 30)
    private val registries = listOf(RegistryDTO(0, time, "title", "body", Mood.VERY_HAPPY),
        RegistryDTO(1, time, "title", "body", Mood.HAPPY),
        RegistryDTO(2, time, "title", "body", Mood.NEUTRAL),
        RegistryDTO(3, time, "title", "body", Mood.SAD),
        RegistryDTO(4, time, "title", "body", Mood.VERY_SAD))

    @Test
    fun givenRegistries_whenSuccess_shouldReturnRegistryVO() {
        val result = registryConverter.convert(registries)
        result.forEach {
            assertEquals("22:30", it.time)
            assertEquals("body", it.body)
            assertEquals("title", it.title)
        }

        assertEquals("Se sentindo muito feliz", result[0].moodDescription)
        assertEquals("Se sentindo feliz", result[1].moodDescription)
        assertEquals("Se sentindo neutro", result[2].moodDescription)
        assertEquals("Se sentindo triste", result[3].moodDescription)
        assertEquals("Se sentindo muito triste", result[4].moodDescription)

        assertEquals(R.drawable.ic_grinning_face_24, result[0].moodRes)
        assertEquals(R.drawable.ic_smiling_face_24, result[1].moodRes)
        assertEquals(R.drawable.ic_neutral_face_24, result[2].moodRes)
        assertEquals(R.drawable.ic_slightly_frowning_face_24, result[3].moodRes)
        assertEquals(R.drawable.ic_crying_face_24, result[4].moodRes)
    }
}