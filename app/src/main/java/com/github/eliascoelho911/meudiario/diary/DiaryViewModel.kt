package com.github.eliascoelho911.meudiario.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.eliascoelho911.meudiario.diary.usecases.GetRegistriesPerDayUseCase
import kotlin.coroutines.CoroutineContext

class DiaryViewModel(
    context: CoroutineContext,
    getRegistriesPerDayUseCase: GetRegistriesPerDayUseCase
) : ViewModel() {
    val registriesPerDay = getRegistriesPerDayUseCase.invoke().asLiveData(context)
}