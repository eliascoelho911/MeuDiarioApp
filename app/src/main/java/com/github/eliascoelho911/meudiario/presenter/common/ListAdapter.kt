package com.github.eliascoelho911.meudiario.presenter.common

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

abstract class ListAdapter<VH: RecyclerView.ViewHolder, T>: RecyclerView.Adapter<VH>() {
    private val list = mutableListOf<T>()

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<T>) {
        list.clear()
        list.addAll(newList)

        notifyDataSetChanged()
    }

    fun getItem(index: Int) = list[index]
}