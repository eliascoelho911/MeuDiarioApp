package com.github.eliascoelho911.meudiario.di

import androidx.room.Room
import com.github.eliascoelho911.meudiario.data.AppDatabase
import com.github.eliascoelho911.meudiario.data.registry.FakeRegistryRepository
import com.github.eliascoelho911.meudiario.data.registry.RegistryRepository
import com.github.eliascoelho911.meudiario.diary.DiaryViewModel
import com.github.eliascoelho911.meudiario.diary.converters.RegistryConverter
import com.github.eliascoelho911.meudiario.diary.adapters.RegistryListAdapter
import com.github.eliascoelho911.meudiario.diary.usecases.GetRegistriesPerDayUseCase
import com.github.eliascoelho911.meudiario.diary.helpers.GroupRegistriesByDay
import com.github.eliascoelho911.meudiario.diary.adapters.RegistryPerDayListAdapter
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "meu-diario-db") }
    single { get<AppDatabase>().registryDao() }
    single<RegistryRepository> { FakeRegistryRepository() }
}

val viewModelModule = module {
    viewModel { DiaryViewModel(Dispatchers.IO, get()) }
}

val helpersModule = module {
    single { RegistryConverter(get()) }
    single { GroupRegistriesByDay(get()) }
}

val listAdaptersModule = module {
    factory { RegistryPerDayListAdapter() }
    factory { RegistryListAdapter() }
}

val useCaseModule = module {
    single { GetRegistriesPerDayUseCase(get(), get()) }
}

val allModules = listOf(dataModule, viewModelModule, helpersModule, listAdaptersModule, useCaseModule)
