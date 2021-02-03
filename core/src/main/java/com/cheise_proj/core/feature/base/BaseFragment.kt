package com.cheise_proj.core.feature.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {
    open fun getToolBar(): Toolbar? = null

    private fun initToolbar() {
        val navHostFragment = findNavController()
        val toolbar = getToolBar()
        navHostFragment.addOnDestinationChangedListener { _, destination, _ ->
            toolbar?.title = destination.label
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

}