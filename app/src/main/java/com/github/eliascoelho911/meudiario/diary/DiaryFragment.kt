package com.github.eliascoelho911.meudiario.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.diary.adapters.RegistryPerDayListAdapter
import com.github.eliascoelho911.meudiario.root.RootActivityManager
import com.github.eliascoelho911.meudiario.screen.Screen
import com.github.eliascoelho911.meudiario.util.addMarginBetweenItems
import com.github.eliascoelho911.meudiario.util.addMaterialDividerItemDecoration
import com.github.eliascoelho911.meudiario.util.setOnActionExpandListener
import com.github.eliascoelho911.meudiario.util.setOnQueryTextChange
import kotlinx.android.synthetic.main.fragment_diary.registriesPerDays
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryFragment : Fragment(), Screen {
    private val registryPerDayListAdapter: RegistryPerDayListAdapter by inject()
    private val rootActivityManager: RootActivityManager by inject()
    private val viewModel by viewModel<DiaryViewModel>()
    private var collapseOnBackPressed: OnBackPressedCallback? = null

    override val title: String by lazy { getString(R.string.name_diary_screen) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_diary, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRegistriesPerDays()
        setupObservers()
        setupToolbar()
    }

    private fun setupToolbar() {
        with(rootActivityManager) {
            setToolbarMenuRes(R.menu.default_toolbar_menu)
            findToolbarMenuItem(R.id.action_search)?.run {
                onCloseSearch()
                setupListeners()
                setupSearch()
            }
        }
    }

    private fun MenuItem.setupSearch() {
        actionView.run {
            this as SearchView
            setOnQueryTextChange { query ->
                filterRegistriesByQuery(query)
                false
            }
        }
    }

    private fun MenuItem.setupListeners() {
        setOnMenuItemClickListener {
            if (it.itemId == R.id.action_search) {
                rootActivityManager.hide()
                enableCollapseOnBackPressed()
            }
            true
        }
    }

    private fun MenuItem.onCloseSearch() {
        setOnActionExpandListener(onMenuItemActionCollapse = {
            rootActivityManager.show()
            disableCollapseOnBackPressed()
            true
        })
    }

    private fun filterRegistriesByQuery(query: String) {
        registryPerDayListAdapter.submitList(viewModel.searchRegistries(query))
    }

    private fun setupObservers() {
        registriesPerDayObserver()
    }

    private fun registriesPerDayObserver() {
        viewModel.registriesPerDay.observe(viewLifecycleOwner) {
            registryPerDayListAdapter.submitList(it)
        }
    }

    private fun setupRegistriesPerDays() {
        registriesPerDays.apply {
            adapter = registryPerDayListAdapter
            addMaterialDividerItemDecoration()
            addMarginBetweenItems(R.dimen.size_8)
        }
    }

    private fun disableCollapseOnBackPressed() {
        collapseOnBackPressed?.remove()
        collapseOnBackPressed = null
    }

    private fun enableCollapseOnBackPressed() {
        if (collapseOnBackPressed == null)
            collapseOnBackPressed = requireActivity().onBackPressedDispatcher.addCallback {
                rootActivityManager.collapseToolbarActionView()
            }
    }
}
