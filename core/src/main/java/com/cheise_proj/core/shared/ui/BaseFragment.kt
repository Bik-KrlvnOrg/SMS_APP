package com.cheise_proj.core.shared.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.core.shared.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.*
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

    protected fun showProgress(view: ProgressBar, it: Boolean?) {
        it?.let {
            if (it) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
    }

    protected fun showMessage(view: Snackbar, value: String?) {
        value?.let {
            login.isEnabled = false
            view.setText(it).show()
        }
    }

}