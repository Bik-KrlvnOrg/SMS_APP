package com.cheise_proj.core.feature.student.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.cheise_proj.core.feature.student.notification.adapter.AnnouncementSlider
import com.cheise_proj.core.feature.student.notification.adapter.NotificationAdapter
import com.cheise_proj.core.feature.student.notification.vo.AnnouncementItem
import com.cheise_proj.core.feature.student.notification.vo.NotificationItem
import kotlinx.android.synthetic.main.fragment_notification.*

class NotificationFragment : BaseFragment() {
    private lateinit var _adapter: NotificationAdapter
    private lateinit var _sliderAdapter: AnnouncementSlider

    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initRecyclerView()
    }
//
    private fun initViewPager() {
        val dataSize = AnnouncementItem.getAnnouncementData().size
        _sliderAdapter = AnnouncementSlider(childFragmentManager, lifecycle, dataSize)
        view_pager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = _sliderAdapter
        }
    }

    private fun initRecyclerView() {
        _adapter = NotificationAdapter {}
        _adapter.submitList(NotificationItem.getNotificationData())
        recycler_view.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context)
        }

    }

}