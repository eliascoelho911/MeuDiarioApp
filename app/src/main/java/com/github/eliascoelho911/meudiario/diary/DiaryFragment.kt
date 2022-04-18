package com.github.eliascoelho911.meudiario.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.screen.Screen

class DiaryFragment: Fragment(), Screen {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_diary, container, false)

    override val title: String by lazy { getString(R.string.name_diary_screen) }
}