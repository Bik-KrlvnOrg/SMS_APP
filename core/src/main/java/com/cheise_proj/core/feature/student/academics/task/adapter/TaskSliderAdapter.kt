package com.cheise_proj.core.feature.student.academics.task.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cheise_proj.core.feature.student.academics.task.TaskSliderStudentFragment

class TaskSliderAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val count: Int
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment {
        return TaskSliderStudentFragment.newInstance(position)
    }
}