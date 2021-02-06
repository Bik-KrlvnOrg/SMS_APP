package com.cheise_proj.core.feature.student.academics.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.academics.task.vo.TaskSliderItem
import kotlinx.android.synthetic.main.fragment_student_task_slider.*

private const val ARG_Position = "position"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskSliderStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskSliderStudentFragment : Fragment() {
    private var itemPosition: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemPosition = it.getInt(ARG_Position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_task_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        if (itemPosition == -1) return
        val item = TaskSliderItem.getTask(itemPosition!!)
        header.text = item.subject
        sub_header.text = item.description
        item_1.text = item.teacherName
        item_2.text = item.teacherTitle
        item_3.text = item.date
        avatar.setImageDrawable(ContextCompat.getDrawable(requireContext(),item.image!!))
        task_card.setOnClickListener {
            navigateToTaskDetail()
        }

    }

    private fun navigateToTaskDetail() {
        findNavController().navigate(R.id.action_taskStudentFragment_to_taskDetailStudentFragment)
    }


    companion object {
        /**
         * @param itemPosition Parameter 1.
         * @return A new instance of fragment TaskSliderStudentFragment.
         */
        @JvmStatic
        fun newInstance(itemPosition: Int) =
            TaskSliderStudentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_Position, itemPosition)
                }
            }
    }
}