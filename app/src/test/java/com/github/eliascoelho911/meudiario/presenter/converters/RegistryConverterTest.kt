package com.github.eliascoelho911.meudiario.presenter.converters

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.common.TestCustomApplication
import com.github.eliascoelho911.meudiario.domain.entities.Mood
import com.github.eliascoelho911.meudiario.domain.entities.Registry
import com.github.eliascoelho911.meudiario.presenter.diary.vo.RegistryVO
import java.time.LocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestCustomApplication::class)
class RegistryConverterTest {
    private val context: Context by lazy { ApplicationProvider.getApplicationContext() }
    private val converter by lazy { RegistryConverter(context) }
    private val dateTime = LocalDateTime.of(2020, 1, 1, 22, 30)
    private val originalData =
        listOf(Registry(0, dateTime, "title", "body", Mood.NEUTRAL))
    private val convertedData = listOf(RegistryVO(0,
        dateTime,
        "22:30",
        "title",
        "body",
        "Se sentindo neutro",
        R.drawable.ic_neutral_face_24))

    @Test
    fun givenSuccess_whenConvertOriginalData_shouldReturnDataConverted() {
        assertEquals(convertedData, converter.convert(originalData))
    }
}