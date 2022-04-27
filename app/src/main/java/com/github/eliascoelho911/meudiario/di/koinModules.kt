package com.github.eliascoelho911.meudiario.di

import androidx.room.Room
import com.github.eliascoelho911.meudiario.data.AppDatabase
import com.github.eliascoelho911.meudiario.data.repository.FakeRegistryRepository
import com.github.eliascoelho911.meudiario.domain.repositories.RegistryRepository
import com.github.eliascoelho911.meudiario.presenter.diary.DiaryViewModel
import com.github.eliascoelho911.meudiario.presenter.diary.adapter.RegistryListAdapter
import com.github.eliascoelho911.meudiario.presenter.diary.adapter.RegistryPerDayListAdapter
import com.github.eliascoelho911.meudiario.presenter.converters.RegistryConverter
import com.github.eliascoelho911.meudiario.domain.usecases.GetRegistriesPerDayUseCase
import com.github.eliascoelho911.meudiario.domain.usecases.SearchRegistriesPerDayByQueryUseCase
import com.github.eliascoelho911.meudiario.presenter.converters.RegistryPerDayConverter
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "meu-diario-db") }
    single { get<AppDatabase>().registryDao() }
    single<RegistryRepository> { FakeRegistryRepository() }
}

val presenterModule = module {
    viewModel { DiaryViewModel(Dispatchers.IO, get(), get(), get()) }
    single { RegistryConverter(get()) }
    single { RegistryPerDayConverter() }
    factory { RegistryPerDayListAdapter() }
    factory { RegistryListAdapter() }
}

val domainModule = module {
    single { GetRegistriesPerDayUseCase(get(), get()) }
    single { SearchRegistriesPerDayByQueryUseCase(get()) }
}

val allModules = listOf(dataModule,
    presenterModule,
    domainModule)
