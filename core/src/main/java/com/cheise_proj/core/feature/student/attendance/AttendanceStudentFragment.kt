package com.cheise_proj.core.feature.student.attendance

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.base.BaseFragment
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_student_attendance.*
import java.util.*


class AttendanceStudentFragment : BaseFragment() {
    private lateinit var calendarView: CompactCalendarView
    private var snackbar: Snackbar? = null
    override fun getToolBar(): Toolbar? = toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val root: View = view.findViewById(R.id.root)
        calendarView = view.findViewById(R.id.calendar_view)
        snackbar = Snackbar.make(root, "", Snackbar.LENGTH_SHORT)

        initView()
    }

    private fun initView() {
        val events = arrayListOf(
            Event(Color.GREEN, 1618444800000, "Child Present"),
            Event(Color.GREEN, 1618617600000, "Child Present"),
            Event(Color.GREEN, 1617840000000, "Child Present"),
            Event(Color.GREEN, 1617926400000, "Child Present"),
            Event(Color.MAGENTA, 1618876800000, "Child Absent"),
            Event(Color.MAGENTA, 1617580800000, "Child Absent"),
            Event(Color.MAGENTA, 1617235200000, "Child Absent")
        )
        item_4.text = "3"
        item_1.text = "4"
        calendarView.apply {
            this.addEvents(events)
        }
        calendarView.addEvents(events)
        calendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(p0: Date?) {
                val events = calendarView.getEvents(p0)
                if (events.size > 0) {
                    snackbar?.setText(events[0].data.toString())
                    snackbar?.show()
                }
            }

            override fun onMonthScroll(p0: Date?) {

            }
        })

    }


}