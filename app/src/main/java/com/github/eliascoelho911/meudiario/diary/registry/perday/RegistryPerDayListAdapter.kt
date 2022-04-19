package com.github.eliascoelho911.meudiario.diary.registry.perday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.diary.registry.perday.RegistryPerDayListAdapter.ViewHolder

class RegistryPerDayListAdapter : ListAdapter<RegistryPerDayVO, ViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_registry_per_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textDay: TextView by lazy { itemView.findViewById(R.id.textDay) }
        private val textMonth: TextView by lazy { itemView.findViewById(R.id.textMonth) }
        private val registriesList: RecyclerView by lazy { itemView.findViewById(R.id.registries) }

        fun bind(data: RegistryPerDayVO) {
            textDay.text = data.day
            textMonth.text = data.month
        }
    }

    object DiffUtil : ItemCallback<RegistryPerDayVO>() {
        override fun areItemsTheSame(
            oldItem: RegistryPerDayVO,
            newItem: RegistryPerDayVO,
        ): Boolean = oldItem.day == newItem.day && oldItem.month == newItem.month

        override fun areContentsTheSame(
            oldItem: RegistryPerDayVO,
            newItem: RegistryPerDayVO,
        ): Boolean = oldItem == newItem
    }
}