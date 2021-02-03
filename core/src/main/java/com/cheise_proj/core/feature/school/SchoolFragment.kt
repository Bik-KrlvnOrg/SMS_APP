package com.cheise_proj.core.feature.school

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.cheise_proj.core.feature.school.adapter.SchoolAdapter
import com.cheise_proj.core.feature.school.vo.SchoolItem
import kotlinx.android.synthetic.main.fragment_school.*


class SchoolFragment : BaseFragment() {
    private lateinit var _adapter: SchoolAdapter
    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        _adapter = SchoolAdapter {}
        _adapter.submitList(
            arrayListOf(
                SchoolItem(1, "Email", "school@me.com"),
                SchoolItem(2, "Website", "www.school.com"),
                SchoolItem(3, "Box Number", "Box 32,ksi"),
                SchoolItem(4, "Student Capacity", "500"),
            )
        )
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = _adapter
        }
    }


}