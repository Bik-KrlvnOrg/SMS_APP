package com.cheise_proj.core.shared.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.core.shared.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    protected lateinit var viewModel: VM

    abstract fun getViewModel(): Class<out VM>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[getViewModel()]
    }

}