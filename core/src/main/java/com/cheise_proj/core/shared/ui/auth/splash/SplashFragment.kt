package com.cheise_proj.core.shared.ui.auth.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cheise_proj.core.R
import com.cheise_proj.core.common.AuthType
import com.cheise_proj.core.domain.model.User
import com.cheise_proj.core.shared.ui.BaseFragment


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class SplashFragment : BaseFragment<SplashViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.userData.observe(viewLifecycleOwner, {
            navigateAuth(it)
        })
    }

    private fun navigateAuth(user: User?) {
        user?.let {
            when (AuthType.valueOf(it.type!!)) {
                AuthType.STUDENT -> {
                    navigateToStudentDashboard()
                }
                AuthType.STAFF -> {
                }
                AuthType.ADMIN -> {
                }
            }
        }?: run{navigateLogin()}

    }

    private fun navigateLogin() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun navigateToStudentDashboard() {
        findNavController().navigate(R.id.action_splashFragment_to_studentActivity)
        requireActivity().finish()
    }

    override fun getViewModel(): Class<out SplashViewModel> = SplashViewModel::class.java

}