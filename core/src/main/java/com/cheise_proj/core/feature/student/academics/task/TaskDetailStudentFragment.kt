package com.cheise_proj.core.feature.student.academics.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.cheise_proj.core.feature.student.academics.task.adapter.TaskAdapter
import com.cheise_proj.core.feature.student.academics.task.vo.TaskItem
import kotlinx.android.synthetic.main.fragment_student_task_detail.*
import kotlinx.android.synthetic.main.task_header_view.*


class TaskDetailStudentFragment : BaseFragment() {
    private lateinit var _adapter: TaskAdapter

    override fun getToolBar(): Toolbar? = toolbar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHeaderView()
    }

    private fun initHeaderView() {
        val data = TaskItem.getTaskData()[0]
        val taskInfo = data.taskInfo
        header.text = taskInfo.subject
        sub_header.text = taskInfo.description
        item_1.text = taskInfo.teacherName
        item_2.text = taskInfo.teacherTitle
        item_3.text = taskInfo.date
        avatar.setImageDrawable(ContextCompat.getDrawable(requireContext(), taskInfo.image!!))

        initBodyView()
    }

    private fun initBodyView() {
        val data = TaskItem.getTaskData()[0]
        item_description.text = data.description
        initRecyclerView(data)
    }

    private fun initRecyclerView(data: TaskItem) {
        _adapter = TaskAdapter {}
        data.status = ""
        _adapter.submitList(mutableListOf(data,data))
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = _adapter
        }
    }


}