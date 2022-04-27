package com.github.eliascoelho911.meudiario.root

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RootActivityManager(
    private val bottomNavigation: BottomNavigationView,
    private val toolbar: Toolbar,
    private val fab: FloatingActionButton,
) {
    fun show(bottomNavigation: Boolean = true, fab: Boolean = true) {
        if (bottomNavigation) showBottomNavigation() else hideBottomNavigation()
        if (fab) showFab() else hideFab()
    }

    fun hide(bottomNavigation: Boolean = true, fab: Boolean = true) {
        show(bottomNavigation.not(), fab.not())
    }

    fun hideBottomNavigation() {
        bottomNavigation.isVisible = false
    }

    fun showBottomNavigation() {
        bottomNavigation.isVisible = true
    }

    fun setToolbarMenuRes(@MenuRes menuRes: Int) {
        toolbar.inflateMenu(menuRes)
    }

    fun removeToolbarMenu() {
        setToolbarMenuRes(0)
    }

    fun hideFab() {
        fab.hide()
    }

    fun showFab() {
        fab.show()
    }

    fun findToolbarMenuItem(@IdRes id: Int): MenuItem? = toolbar.menu.findItem(id)

    fun collapseToolbarActionView() {
        toolbar.collapseActionView()
    }
}