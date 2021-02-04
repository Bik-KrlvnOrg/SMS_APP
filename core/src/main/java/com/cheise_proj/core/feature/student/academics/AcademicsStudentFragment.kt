package com.cheise_proj.core.feature.student.academics

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
import kotlinx.android.synthetic.main.fragment_academics_student.*

class AcademicsStudentFragment : BaseFragment() {

    private lateinit var _adapter: DashboardAdapter

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academics_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        _adapter = DashboardAdapter {
            it?.let {
                if (it.actionId != null) {
                    findNavController().navigate(it.actionId!!)
                }
            }
        }
        _adapter.submitList(
            arrayListOf(
                DashboardItem(
                    1,
                    "Task",
                    R.drawable.book,
                    R.id.action_academicsStudentFragment_to_taskStudentMainFragment
                ),
                DashboardItem(
                    1,
                    "Examination",
                    R.drawable.examination,
                    R.id.action_academicsStudentFragment_to_examinationMainFragment
                ),
                DashboardItem(1, "Timetable", R.drawable.timetable),
                DashboardItem(1, "Report", R.drawable.achievement),
            )
        )
        recycler_view.apply {
            val spanCount = 2
            adapter = _adapter
            layoutManager = GridLayoutManager(context, spanCount)
        }

    }


}