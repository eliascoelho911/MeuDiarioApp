package com.github.eliascoelho911.meudiario.common

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class TestCustomApplication : Application() {
    private var koinApplication: KoinApplication? = null

    override fun onCreate() {
        super.onCreate()
        stopKoin()
        koinApplication = startKoin {
            androidContext(this@TestCustomApplication)
        }
    }

    fun loadModules(modules: List<Module>) {
        koinApplication?.modules(modules)
    }
}