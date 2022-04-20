package com.github.eliascoelho911.meudiario.common

import androidx.annotation.IdRes
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.eliascoelho911.meudiario.di.allModules

fun clickOn(@IdRes viewId: Int) {
    onView(withId(viewId)).perform(click())
}

fun loadAllModulesOnKoin() {
    ApplicationProvider.getApplicationContext<TestCustomApplication>().loadModules(allModules)
}