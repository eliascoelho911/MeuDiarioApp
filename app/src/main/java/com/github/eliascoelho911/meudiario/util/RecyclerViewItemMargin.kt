package com.github.eliascoelho911.meudiario.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class RecyclerViewItemMargin(
    private val context: Context,
    @DimenRes private val marginRes: Int,
) : ItemDecoration() {
    private val margin by lazy { context.resources.getDimensionPixelSize(marginRes) }

    var isLastItemDecoration = false

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val childCount = parent.adapter?.itemCount!!

        if (childCount == 1) {
            applyMargin(outRect, top = false, bottom = isLastItemDecoration)
        } else {
            when (parent.getChildLayoutPosition(view)) {
                0 -> {
                    applyMargin(outRect, top = false)
                }
                childCount - 1 -> {
                    applyMargin(outRect, bottom = isLastItemDecoration)
                }
                else -> {
                    applyMargin(outRect)
                }
            }
        }
    }

    private fun applyMargin(outRect: Rect, bottom: Boolean = true, top: Boolean = true) {
        if (bottom)
            outRect.bottom = margin
        if (top)
            outRect.top = margin
    }
}

fun RecyclerView.addMarginBetweenItems(@DimenRes dimenRes: Int, applyInLastItem: Boolean = false) {
    val itemDecoration = RecyclerViewItemMargin(context, dimenRes).apply {
        isLastItemDecoration = applyInLastItem
    }
    addItemDecoration(itemDecoration)
}