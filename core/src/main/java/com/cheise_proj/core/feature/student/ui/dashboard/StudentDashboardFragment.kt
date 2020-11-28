package com.cheise_proj.core.feature.student.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.core.R

class StudentDashboardFragment : Fragment() {

    private lateinit var studentDashboardViewModel: StudentDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentDashboardViewModel =
            ViewModelProvider(this).get(StudentDashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.student_fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        studentDashboardViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}