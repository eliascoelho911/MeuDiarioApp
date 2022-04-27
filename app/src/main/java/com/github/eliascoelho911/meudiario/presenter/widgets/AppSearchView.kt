package com.github.eliascoelho911.meudiario.presenter.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.github.eliascoelho911.meudiario.R

class AppSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : SearchView(context, attrs, defStyleAttr) {
    override fun onAttachedToWindow() {
        changeTextAppearance()
        changeQueryHintToDefault()
        super.onAttachedToWindow()
    }

    private fun changeQueryHintToDefault() {
        queryHint = context.getString(R.string.cd_search)
    }

    private fun changeTextAppearance() {
        val searchText = findViewById<View>(androidx.appcompat.R.id.search_src_text) as TextView
        searchText.setTextAppearance(R.style.AppTheme_TextAppearance_BodyLarge)
    }
}