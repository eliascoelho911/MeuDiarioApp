package com.github.eliascoelho911.meudiario.presenter.root

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.presenter.diary.DiaryFragment
import com.github.eliascoelho911.meudiario.presenter.Screen
import com.github.eliascoelho911.meudiario.presenter.common.getFragmentState
import com.github.eliascoelho911.meudiario.presenter.common.saveFragmentState
import kotlinx.android.synthetic.main.activity_root.bottomNavigation
import kotlinx.android.synthetic.main.activity_root.toolbar
import kotlinx.android.synthetic.main.fragment_diary.fabAddRegistry
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private const val SELECTED_ITEM_ON_TAB = "SELECTED_ITEM_ON_TAB"
private const val DEFAULT_SCREEN = R.id.menu_diary_screen

class RootActivity : AppCompatActivity(R.layout.activity_root) {
    @IdRes
    private var selectedItemOnTab = DEFAULT_SCREEN
    private var fragmentState: Fragment.SavedState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleSavedInstanceState(savedInstanceState)
        openFragmentById(selectedItemOnTab)
        setupBottomNavigation()
        loadManagersOnKoin()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.saveFragmentState(supportFragmentManager, R.id.container)
        outState.saveSelectedItemOnTab()
        super.onSaveInstanceState(outState)
    }

    private fun loadManagersOnKoin() {
        loadKoinModules(module {
            single { RootActivityManager(bottomNavigation, toolbar, fabAddRegistry) }
        })
    }

    private fun handleSavedInstanceState(savedInstanceState: Bundle?) {
        selectedItemOnTab = savedInstanceState?.getSelectedItemOnTab() ?: DEFAULT_SCREEN
        fragmentState = savedInstanceState?.getFragmentState(R.id.container)
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener {
            openFragmentById(it.itemId)
            true
        }
    }

    private fun openFragmentById(@IdRes id: Int) {
        when (id) {
            R.id.menu_diary_screen -> openFragment(DiaryFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.commitNow {
            fragmentState?.let {
                fragment.setInitialSavedState(it)
                fragmentState = null
            }
            replace(R.id.container, fragment)
        }
        updateToolbarTitle(fragment)
    }

    private fun updateToolbarTitle(fragment: Fragment) {
        if (fragment is Screen) toolbar.title = fragment.title
    }

    private fun Bundle.saveSelectedItemOnTab() {
        putInt(SELECTED_ITEM_ON_TAB, bottomNavigation.selectedItemId)
    }

    @IdRes
    private fun Bundle.getSelectedItemOnTab() = getInt(SELECTED_ITEM_ON_TAB, DEFAULT_SCREEN)
}