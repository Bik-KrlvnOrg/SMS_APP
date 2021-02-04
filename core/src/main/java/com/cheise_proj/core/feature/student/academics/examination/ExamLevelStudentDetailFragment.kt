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
import kotlinx.android.synthetic.main.fragment_student_exam_level_detail.*

class ExamLevelStudentDetailFragment : BaseFragment() {
    private lateinit var _adapter: ExaminationAdapter

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_exam_level_detail, container, false)
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
                    1,
                    "Semester 1",
                    "Exam",
                    R.id.action_examLevelDetailFragment_to_examinationDetailFragment

                ),
                ExaminationItem(
                    2,
                    "Semester 2",
                    "Exam",
                    R.id.action_examLevelDetailFragment_to_examinationDetailFragment
                ),
                ExaminationItem(
                    3,
                    "Semester 3",
                    "Exam",
                    R.id.action_examLevelDetailFragment_to_examinationDetailFragment
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