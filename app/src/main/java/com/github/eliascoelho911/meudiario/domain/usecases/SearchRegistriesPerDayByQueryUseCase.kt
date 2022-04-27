package com.github.eliascoelho911.meudiario.domain.usecases

import kotlinx.coroutines.flow.map

class SearchRegistriesPerDayByQueryUseCase(
    private val getRegistriesPerDayUseCase: GetRegistriesPerDayUseCase,
) {
    private val registriesPerDay by lazy { getRegistriesPerDayUseCase.invoke() }

    fun invoke(query: String) = registriesPerDay.map { list ->
        list.map { perDayIt ->
            perDayIt.copy(registries = perDayIt.registries.filter {
                it.title.contains(query, ignoreCase = false) or
                        it.body.contains(query, ignoreCase = true)
            })
        }.filter { it.registries.isNotEmpty() }
    }
}