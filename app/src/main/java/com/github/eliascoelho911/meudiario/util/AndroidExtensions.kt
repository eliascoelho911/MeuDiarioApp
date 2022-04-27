package com.github.eliascoelho911.meudiario.util

import android.animation.Animator
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewPropertyAnimator
import androidx.annotation.IdRes
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration

private const val FRAGMENT_STATE = "FRAGMENT_STATE"

fun Bundle.saveFragmentState(fragmentManager: FragmentManager, @IdRes viewId: Int) {
    fragmentManager.findFragmentById(viewId)?.let {
        val state = fragmentManager.saveFragmentInstanceState(it)
        putParcelable(FRAGMENT_STATE + viewId, state)
    }
}

fun Bundle.getFragmentState(@IdRes viewId: Int): Fragment.SavedState? =
    getParcelable(FRAGMENT_STATE + viewId)


fun RecyclerView.addMaterialDividerItemDecoration(isLastItemDecorated: Boolean = false) {
    val itemDecoration =
        MaterialDividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
            this.isLastItemDecorated = isLastItemDecorated
        }
    addItemDecoration(itemDecoration)
}

inline fun SearchView.setOnQueryTextChange(crossinline block: (newText: String) -> Boolean) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean = false

        override fun onQueryTextChange(newText: String): Boolean = block(newText)
    })
}

fun ViewPropertyAnimator.setListener(
    onAnimationStart: () -> Unit = {},
    onAnimationEnd: () -> Unit = {},
    onAnimationCancel: () -> Unit = {},
    onAnimationRepeat: () -> Unit = {},
) = setListener(object : Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator?) {
        onAnimationStart()
    }

    override fun onAnimationEnd(animation: Animator?) {
        onAnimationEnd()
    }

    override fun onAnimationCancel(animation: Animator?) {
        onAnimationCancel()
    }

    override fun onAnimationRepeat(animation: Animator?) {
        onAnimationRepeat()
    }
})

fun MenuItem.setOnActionExpandListener(
    onMenuItemActionExpand: () -> Boolean = { true },
    onMenuItemActionCollapse: () -> Boolean = { true },
) {
    setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?) =
            onMenuItemActionExpand()

        override fun onMenuItemActionCollapse(item: MenuItem?) =
            onMenuItemActionCollapse()
    })
}