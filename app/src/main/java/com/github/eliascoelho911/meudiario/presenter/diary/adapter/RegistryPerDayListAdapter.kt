package com.github.eliascoelho911.meudiario.presenter.diary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.presenter.diary.adapter.RegistryPerDayListAdapter.ViewHolder
import com.github.eliascoelho911.meudiario.presenter.diary.vo.RegistryPerDayVO
import com.github.eliascoelho911.meudiario.presenter.common.ListAdapter
import com.github.eliascoelho911.meudiario.presenter.common.addMarginBetweenItems
import com.github.eliascoelho911.meudiario.presenter.common.addMaterialDividerItemDecoration
import org.koin.java.KoinJavaComponent.inject

class RegistryPerDayListAdapter : ListAdapter<ViewHolder, RegistryPerDayVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_registry_per_day, parent, false)
        return ViewHolder(view).also {
            it.setupRegistriesList()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textDay: TextView by lazy { itemView.findViewById(R.id.textDay) }
        private val textMonth: TextView by lazy { itemView.findViewById(R.id.textMonth) }
        private val containerDate: ViewGroup by lazy { itemView.findViewById(R.id.containerDate) }
        private val registriesList: RecyclerView by lazy { itemView.findViewById(R.id.registries) }

        private val registryListAdapter: RegistryListAdapter by inject(RegistryListAdapter::class.java)

        fun setupRegistriesList() {
            registriesList.apply {
                adapter = registryListAdapter
                addMaterialDividerItemDecoration()
                addMarginBetweenItems(R.dimen.size_8)
            }
        }

        fun bind(data: RegistryPerDayVO) {
            textDay.text = data.day
            textMonth.text = data.month
            containerDate.contentDescription = data.date.toString()
            registryListAdapter.submitList(data.registries)
        }
    }
}