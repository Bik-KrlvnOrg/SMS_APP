package com.cheise_proj.core.shared.ui.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.cheise_proj.core.R
import com.cheise_proj.core.common.AuthType
import com.cheise_proj.core.shared.data.model.Status
import com.cheise_proj.core.shared.ui.BaseFragment
import com.cheise_proj.domain.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment : BaseFragment<LoginViewModel>() {

    private lateinit var snackBar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackBar = Snackbar.make(root, "", Snackbar.LENGTH_SHORT)
        username.addTextChangedListener(liveWatcher(viewModel.onUsernameChange))
        password.addTextChangedListener(liveWatcher(viewModel.onPasswordChange))
        auth_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.onSelectionChange.value = auth_type.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        login.setOnClickListener { viewModel.onLogin() }
        subscribeObserver()
    }

    private fun liveWatcher(onTextChange: MutableLiveData<String>): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                onTextChange.value = p0.toString()
            }
        }
    }

    private fun subscribeObserver() {
        viewModel.message.observe(viewLifecycleOwner, { showMessage(snackBar, it) })
        viewModel.username.observe(viewLifecycleOwner, {})
        viewModel.password.observe(viewLifecycleOwner, {})
        viewModel.userData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> showProgress(loading, true)
                Status.ERROR -> {
                    showProgress(loading, false)
                    showMessage(snackBar, it.message)
                }
                Status.SUCCESS -> {
                    showProgress(loading, false)
                    navigateUser(it.data)

                }
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { showProgress(loading, it) })
        viewModel.usernameError.observe(viewLifecycleOwner, { showErrorMessage(username, it) })
        viewModel.passwordError.observe(viewLifecycleOwner, { showErrorMessage(password, it) })
        viewModel.canExecute.observe(viewLifecycleOwner, {
            Timber.i("canEx: $it")
            login.isEnabled = it
        })
    }

    private fun navigateUser(user: User?) {
        user?.let {
            when (AuthType.valueOf(it.type!!)) {
                AuthType.STUDENT -> {
                    navigateStudentDashboard()
                }
                AuthType.STAFF -> {
                }
                AuthType.ADMIN -> {
                }
            }
        }
    }

    private fun navigateStudentDashboard() {
        findNavController().navigate(R.id.action_loginFragment_to_studentActivity)
        requireActivity().finish()
    }

    private fun showErrorMessage(view: EditText, value: String?) {
        value?.let {
            login.isEnabled = false
            view.error = it
        }
    }

    override fun getViewModel(): Class<out LoginViewModel> = LoginViewModel::class.java
}