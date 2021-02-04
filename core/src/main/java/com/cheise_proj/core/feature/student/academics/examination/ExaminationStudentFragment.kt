package com.cheise_proj.core.feature.student.academics.examination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.cheise_proj.core.feature.student.academics.examination.adapter.ExaminationAdapter
import com.cheise_proj.core.feature.student.academics.examination.vo.ExaminationItem
import kotlinx.android.synthetic.main.fragment_student_examination.*


class ExaminationStudentFragment : BaseFragment() {

    private lateinit var _adapter: ExaminationAdapter

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_examination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        _adapter = ExaminationAdapter {
            it?.let {
                if (it.actionId != null) {
                    findNavController().navigate(it.actionId)
                }
            }
        }
        _adapter.submitList(
            arrayListOf(
                ExaminationItem(
                    4,
                    "Level 100",
                    "2017/2018 Academic Year",
                    R.id.action_examinationStudentFragment_to_examLevelDetailFragment
                ),
                ExaminationItem(
                    3,
                    "Level 200",
                    "2018/2029 Academic Year",
                    R.id.action_examinationStudentFragment_to_examLevelDetailFragment
                ),
                ExaminationItem(
                    2,
                    "Level 300",
                    "2019/2020 Academic Year",
                    R.id.action_examinationStudentFragment_to_examLevelDetailFragment
                ),
                ExaminationItem(
                    1,
                    "Level 400",
                    "2020/2021 Academic Year",
                    R.id.action_examinationStudentFragment_to_examLevelDetailFragment
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