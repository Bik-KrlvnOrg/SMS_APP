package com.cheise_proj.core.feature.student.academics.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.cheise_proj.core.feature.student.academics.task.adapter.TaskAdapter
import com.cheise_proj.core.feature.student.academics.task.adapter.TaskSliderAdapter
import com.cheise_proj.core.feature.student.academics.task.modal.TaskFilterStudentModalFragment
import com.cheise_proj.core.feature.student.academics.task.vo.TaskItem
import com.cheise_proj.core.feature.student.academics.task.vo.TaskSliderItem
import kotlinx.android.synthetic.main.fragment_student_task.*

class TaskStudentFragment : BaseFragment() {
    private lateinit var _sliderAdapter: TaskSliderAdapter
    private lateinit var _adapter: TaskAdapter

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_filter.setOnClickListener { openFilterModal() }
        initViewPager()
        initRecyclerView()
    }

    private fun initViewPager() {
        val dataSize = TaskSliderItem.getTaskList().size
        _sliderAdapter = TaskSliderAdapter(childFragmentManager, lifecycle, dataSize)
        view_pager.apply {
            orientation = ORIENTATION_HORIZONTAL
            adapter = _sliderAdapter
        }
    }

    private fun initRecyclerView() {
        _adapter = TaskAdapter {
            navigateToTaskDetail()
        }
        _adapter.submitList(TaskItem.getTaskData())
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = _adapter
        }
    }

    private fun navigateToTaskDetail() {
        findNavController().navigate(R.id.action_taskStudentFragment_to_taskDetailStudentFragment)
    }

    private fun openFilterModal() {
        val modal = TaskFilterStudentModalFragment.newInstance()
        modal.onFilterClick {
            if (it != null){
                sub_text.text = it
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }else{
                sub_text.text = "All Task"
            }


        }
        modal.show(childFragmentManager, TaskFilterStudentModalFragment.TASK_FILTER_STUDENT_MODAL)
    }


}