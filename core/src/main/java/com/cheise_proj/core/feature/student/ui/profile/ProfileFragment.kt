package com.cheise_proj.core.feature.student.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.ui.profile.adapter.ProfileAdapter
import com.cheise_proj.core.shared.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * A simple [Fragment] subclass.
 */
@ExperimentalCoroutinesApi
class ProfileFragment : BaseFragment<ProfileViewModel>() {

    private lateinit var _adapter: ProfileAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.studentProfile.observe(viewLifecycleOwner, {
            _adapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        _adapter = ProfileAdapter()
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }

    }

    override fun getViewModel(): Class<out ProfileViewModel> = ProfileViewModel::class.java

}