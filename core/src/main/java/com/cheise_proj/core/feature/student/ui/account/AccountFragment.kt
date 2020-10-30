package com.cheise_proj.core.feature.student.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.ui.account.adapter.AccountMenuAdapter
import com.cheise_proj.core.feature.student.ui.account.model.AccountMenu
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {

    private lateinit var _adapter: AccountMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        _adapter = AccountMenuAdapter() {
            it?.let { menu ->
                when (menu.id) {
                    1 -> {
                        navigateToProfile()
                    }
                }
            }
        }
        val data = mutableListOf(
            AccountMenu(1, "Profile"),
            AccountMenu(2, "Fees")
        )
        _adapter.submitList(data)
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            hasFixedSize()
        }
        recycler_view.adapter = _adapter
    }

    private fun navigateToProfile() {
        findNavController().navigate(R.id.action_navigation_account_to_navigation_profile)
    }

}