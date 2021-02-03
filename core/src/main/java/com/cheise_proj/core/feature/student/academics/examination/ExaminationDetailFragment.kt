package com.cheise_proj.core.feature.student.academics.examination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.cheise_proj.core.feature.student.academics.examination.adapter.ExaminationScoreAdapter
import com.cheise_proj.core.feature.student.academics.examination.vo.ExaminationScore
import kotlinx.android.synthetic.main.fragment_examination_detail.*


class ExaminationDetailFragment : BaseFragment() {
    private lateinit var _adapter: ExaminationScoreAdapter

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_examination_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        _adapter = ExaminationScoreAdapter {}
        _adapter.submitList(
            arrayListOf(
                ExaminationScore(1, "Chemistry", "88", "100", "A"),
                ExaminationScore(2, "Computer", "88", "100", "A"),
                ExaminationScore(3, "Physics", "65", "100", "C+"),
                ExaminationScore(4, "Sports", "89", "100", "A"),
                ExaminationScore(5, "Biology", "83", "100", "A"),
                ExaminationScore(6, "Technology", "75", "100", "B+"),
                ExaminationScore(7, "Programming", "70", "100", "A"),
                ExaminationScore(8, "Life Skills", "80", "100", "A"),
                ExaminationScore(9, "English", "90", "100", "A"),
            )
        )
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = _adapter
        }
    }


}