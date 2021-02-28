package com.cheise_proj.core.feature.student.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.cheise_proj.core.feature.student.dashboard.adapter.DashboardAdapter
import com.cheise_proj.core.feature.student.dashboard.vo.DashboardItem
import kotlinx.android.synthetic.main.fragment_student_dashboard.*
import timber.log.Timber


class DashboardStudentFragment : BaseFragment() {
    private lateinit var _adapter: DashboardAdapter

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        _adapter = DashboardAdapter {
            Timber.i("onClickItem: ${it?.id}")
            it?.let {
                if (it.actionId != null){
                    findNavController().navigate(it.actionId!!)
                }
            }
        }
        _adapter.submitList(
            arrayListOf(
                DashboardItem(
                    1,
                    "Attendance",
                    R.drawable.appointment,
                    R.id.action_dashboardFragment_to_attendanceStudentMainFragment
                ),
                DashboardItem(1, "Records", R.drawable.records),
                DashboardItem(1, "Forums", R.drawable.forum),
                DashboardItem(1, "Account Settings", R.drawable.account_setting),
            )
        )
        recycler_view.apply {
            val spanCount = 2
            adapter = _adapter
            layoutManager = GridLayoutManager(context, spanCount)
        }

    }


}