package com.cheise_proj.core.feature.student.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.core.R
import com.cheise_proj.core.shared.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_profile.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment<ProfileViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner,{
            showProgress(loading,it)
        })
        viewModel.studentProfile.observe(viewLifecycleOwner, {
            applyProfileItems(it)
        })

    }

    private fun applyProfileItems(data: HashMap<String, String>) {
        item_img.setImageResource(R.drawable.ic_baseline_account_circle_24)
        for ((key, value) in data) {
            val itemList = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_profile, item_container, false)
            with(itemList) {
                item_header.text = key
                item_subtitle.text = value
                item_img.setImageResource(R.drawable.ic_dashboard_black_24dp)
            }
            item_container.addView(itemList)
        }

    }


    override fun getViewModel(): Class<out ProfileViewModel> = ProfileViewModel::class.java

}