package com.cheise_proj.core.shared.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.cheise_proj.core.R
import com.cheise_proj.core.common.AuthType
import com.cheise_proj.core.databinding.FragmentLoginBinding
import com.cheise_proj.core.domain.model.User
import com.cheise_proj.core.shared.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.util.*

class LoginFragment : BaseFragment<LoginViewModel>() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var snackBar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackBar = Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.userData.observe(viewLifecycleOwner, {
            Timber.i("login_user: $it")
            navigateToDashboard(it)
        })

        viewModel.message.observe(viewLifecycleOwner, ::showMessage)
        viewModel.userTypeError.observe(viewLifecycleOwner, {})
        viewModel.forgetPassword.observe(viewLifecycleOwner, ::navigateToForgetPasswordPage)
    }

    private fun navigateToDashboard(user: User?) {
        user?.let {
            val type = AuthType.valueOf(it.type?.toUpperCase(Locale.getDefault())!!)
            if (type == AuthType.STUDENT) {
                findNavController().navigate(R.id.action_loginFragment_to_studentActivity)
                requireActivity().finish()
            }
        }
    }

    private fun showMessage(it: String?) {
        it?.let { snackBar.setText(it).show() }
    }

    private fun navigateToForgetPasswordPage(onClick: Boolean) {
        val restoreClick = false
        if (onClick) {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            viewModel.forgetPassword.postValue(restoreClick)
        }
    }

    override fun getViewModel(): Class<out LoginViewModel> = LoginViewModel::class.java
}