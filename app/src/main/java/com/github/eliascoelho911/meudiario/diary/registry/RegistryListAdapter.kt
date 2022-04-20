package com.github.eliascoelho911.meudiario.diary.registry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.diary.registry.RegistryListAdapter.ViewHolder

class RegistryListAdapter : ListAdapter<RegistryVO, ViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_registry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView by lazy { itemView.findViewById(R.id.textTitle) }
        private val textBody: TextView by lazy { itemView.findViewById(R.id.textBody) }
        private val textTime: TextView by lazy { itemView.findViewById(R.id.textTime) }
        private val imgMood: ImageView by lazy { itemView.findViewById(R.id.imgMood) }

        fun bind(item: RegistryVO) {
            textTitle.text = item.title
            textBody.text = item.body
            textTime.text = item.time
            imgMood.setImageResource(item.moodRes)
            imgMood.contentDescription = item.moodDescription
        }
    }

    class DiffUtil : ItemCallback<RegistryVO>() {
        override fun areItemsTheSame(oldItem: RegistryVO, newItem: RegistryVO) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RegistryVO, newItem: RegistryVO) =
            oldItem == newItem
    }
}