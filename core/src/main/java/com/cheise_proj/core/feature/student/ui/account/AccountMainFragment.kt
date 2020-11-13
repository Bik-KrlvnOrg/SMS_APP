package com.cheise_proj.core.feature.student.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheise_proj.core.R

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class AccountMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_main, container, false)
    }

}