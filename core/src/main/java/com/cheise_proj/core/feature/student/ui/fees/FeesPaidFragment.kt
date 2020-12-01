package com.cheise_proj.core.feature.student.ui.fees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cheise_proj.core.R
import com.cheise_proj.core.shared.data.model.Status
import com.cheise_proj.core.shared.ui.BaseFragment
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class FeesPaidFragment : BaseFragment<FeesPaidViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fees_paid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserve()
    }

    private fun subscribeObserve() {
        viewModel.feesPaid.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> Timber.i("loading...")
                Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
                Status.SUCCESS -> Timber.i("feesPaid: $it")
            }
        })
    }

    override fun getViewModel(): Class<out FeesPaidViewModel> = FeesPaidViewModel::class.java

}