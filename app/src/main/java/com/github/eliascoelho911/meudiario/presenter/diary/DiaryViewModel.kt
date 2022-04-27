package com.github.eliascoelho911.meudiario.presenter.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.eliascoelho911.meudiario.domain.usecases.GetRegistriesPerDayUseCase
import com.github.eliascoelho911.meudiario.domain.usecases.SearchRegistriesPerDayByQueryUseCase
import com.github.eliascoelho911.meudiario.presenter.converters.RegistryPerDayConverter
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.map

class DiaryViewModel(
    private val context: CoroutineContext,
    private val getRegistriesPerDayUseCase: GetRegistriesPerDayUseCase,
    private val searchRegistriesPerDayByQueryUseCase: SearchRegistriesPerDayByQueryUseCase,
    private val registryPerDayConverter: RegistryPerDayConverter,
) : ViewModel() {
    val registriesPerDay by lazy {
        getRegistriesPerDayUseCase.invoke()
            .map(registryPerDayConverter::convert)
            .asLiveData(context)
    }

    fun searchRegistries(query: String) =
        searchRegistriesPerDayByQueryUseCase.invoke(query)
            .map(registryPerDayConverter::convert)
            .asLiveData(context)
}