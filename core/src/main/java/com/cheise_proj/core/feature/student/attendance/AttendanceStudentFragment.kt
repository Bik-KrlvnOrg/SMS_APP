package com.cheise_proj.core.feature.student.attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_student_attendance.*


class AttendanceStudentFragment : BaseFragment() {

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_attendance, container, false)
    }


}