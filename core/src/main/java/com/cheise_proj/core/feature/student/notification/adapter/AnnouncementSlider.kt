package com.cheise_proj.core.feature.student.notification.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cheise_proj.core.feature.student.notification.AnnouncementSliderFragment

class AnnouncementSlider(fragmentManager: FragmentManager, lifecycle: Lifecycle, val count: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment {
        return AnnouncementSliderFragment.newInstance(position)
    }
}