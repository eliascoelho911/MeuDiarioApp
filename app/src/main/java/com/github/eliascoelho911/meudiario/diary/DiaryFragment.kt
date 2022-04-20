package com.github.eliascoelho911.meudiario.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.diary.adapters.RegistryPerDayListAdapter
import com.github.eliascoelho911.meudiario.screen.Screen
import com.github.eliascoelho911.meudiario.util.addMarginBetweenItems
import com.github.eliascoelho911.meudiario.util.addMaterialDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_diary.containerDiary
import kotlinx.android.synthetic.main.fragment_diary.fabAddRegistry
import kotlinx.android.synthetic.main.fragment_diary.registriesPerDays
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryFragment : Fragment(), Screen {
    private val registryPerDayListAdapter: RegistryPerDayListAdapter by inject()
    private val viewModel by viewModel<DiaryViewModel>()

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
        autoExtendFabOnScrollChange()
    }

    private fun autoExtendFabOnScrollChange() {
        containerDiary.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            val dy = oldScrollY - scrollY

            if (dy > 0) {
                if (!fabAddRegistry.isExtended)
                    fabAddRegistry.extend()
            } else {
                if (fabAddRegistry.isExtended)
                    fabAddRegistry.shrink()
            }
        }
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
}
