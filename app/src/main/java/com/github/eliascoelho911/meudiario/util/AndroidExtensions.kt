package com.github.eliascoelho911.meudiario.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

private const val FRAGMENT_STATE = "FRAGMENT_STATE"

fun Bundle.saveFragmentState(fragmentManager: FragmentManager, @IdRes viewId: Int) {
    fragmentManager.findFragmentById(viewId)?.let {
        val state = fragmentManager.saveFragmentInstanceState(it)
        putParcelable(FRAGMENT_STATE + viewId, state)
    }
}

fun Bundle.getFragmentState(@IdRes viewId: Int): Fragment.SavedState? =
    getParcelable(FRAGMENT_STATE + viewId)