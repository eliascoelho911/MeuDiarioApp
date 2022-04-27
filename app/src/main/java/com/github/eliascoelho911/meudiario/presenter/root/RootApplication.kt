package com.github.eliascoelho911.meudiario.presenter.root

import android.app.Application
import com.github.eliascoelho911.meudiario.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RootApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RootApplication)
            modules(allModules)
        }
    }
}