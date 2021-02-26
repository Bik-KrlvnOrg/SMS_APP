package com.cheise_proj.core.feature.student.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.notification.vo.AnnouncementItem
import kotlinx.android.synthetic.main.fragment_announcement_slider.*
import java.time.format.DateTimeFormatter

private const val ARG_POSITION = "position"

/**
 * A simple [Fragment] subclass.
 * Use the [AnnouncementSliderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnnouncementSliderFragment : Fragment() {
    private var position: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        if (position == -1) return
        val selectedData = AnnouncementItem.getSelectedData(position!!)
        item_1.text = selectedData.header
        item_2.text = selectedData.body
        item_3.text = selectedData.caption
        item_4.text = selectedData.dateTime.format(DateTimeFormatter.ofPattern("eeee, d MMMM uuuu"))
        item_5.text = selectedData.dateTime.format(DateTimeFormatter.ofPattern("hh:mm"))
        item_6.text = selectedData.venue
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_announcement_slider, container, false)
    }

    companion object {
        /**
         * @param position Parameter 1.
         * @return A new instance of fragment AnnouncementSliderFragment.
         */
        @JvmStatic
        fun newInstance(position: Int) =
            AnnouncementSliderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}