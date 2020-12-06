package com.cheise_proj.core.feature.student.ui.fees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.shared.data.model.Status
import com.cheise_proj.core.shared.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_fees_paid.*

/**
 * A simple [Fragment] subclass.
 */
class FeesPaidFragment : BaseFragment<FeesPaidViewModel>() {

    private lateinit var _adapter: FeesPaidAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fees_paid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObserve()
    }

    private fun initRecyclerView() {
        _adapter = FeesPaidAdapter()
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = _adapter
        }
    }

    private fun subscribeObserve() {
        viewModel.feesPaid.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    showProgress(loading, true)
                }
                Status.ERROR -> {
                    showProgress(loading, false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                        .show()
                }
                Status.SUCCESS -> {
                    _adapter.submitList(it.data)
                    recycler_view.adapter = _adapter
                    showProgress(loading, false)
                }
            }
        })
    }

    override fun getViewModel(): Class<out FeesPaidViewModel> = FeesPaidViewModel::class.java

}