package com.cheise_proj.core.feature.student.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.account.adapter.AccountAdapter
import com.cheise_proj.core.feature.student.account.vo.AccountItem
import kotlinx.android.synthetic.main.fragment_student_account.*

class AccountStudentFragment : Fragment() {
    private lateinit var _adapter: AccountAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        _adapter = AccountAdapter {
            it?.let {
                if (it.actionId != null) {
                    findNavController().navigate(it.actionId!!)
                }
            }
        }
        _adapter.submitList(
            arrayListOf(
                AccountItem(
                    1,
                    "Profile",
                    R.drawable.ic_baseline_account_circle_24,
                    R.id.action_accountStudentFragment_to_profileStudentFragment
                ),
                AccountItem(2, "Fees", R.drawable.ic_baseline_monetization_on_24),
                AccountItem(3, "Transport", R.drawable.ic_baseline_directions_car_24),
                AccountItem(4, "Library", R.drawable.ic_baseline_local_library_24),
                AccountItem(
                    5,
                    "School",
                    R.drawable.ic_baseline_school_24,
                    R.id.action_accountStudentFragment_to_schoolFragment
                ),
            )
        )
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = _adapter
        }

    }


}