package com.cheise_proj.core.feature.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController

abstract class BaseFragment:Fragment() {
    open fun getToolBar(): Toolbar? = null
    open fun getToolBarText(): String? = "Base Text"

    private fun initToolbar() {
        val navHostFragment = findNavController()
        val toolbar = getToolBar()
//        toolbar?.title = getToolBarText()
        toolbar?.setupWithNavController(navController = navHostFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

}