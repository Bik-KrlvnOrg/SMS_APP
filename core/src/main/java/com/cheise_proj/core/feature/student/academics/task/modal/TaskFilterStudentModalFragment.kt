package com.cheise_proj.core.feature.student.academics.task.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.core.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_student_task_filter_modal.*

/**
 * A simple [Fragment] subclass.
 * Use the [TaskFilterStudentModalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskFilterStudentModalFragment : BottomSheetDialogFragment() {
    private var onItemClick: (String?) -> Unit = {}
    private var selectedItem: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_task_filter_modal, container, false)
    }

    internal fun onFilterClick(callback: (String?) -> Unit) {
        onItemClick = callback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_close.setOnClickListener { dismiss() }
        applyCourseChips(
            arrayOf(
                "English",
                "Biology",
                "Chemistry",
                "Physics",
                "Art",
                "Sport",
                "Computer",
                "Economics",
                "Math",
                "History"
            )
        )
        applyFileTypeChips(arrayOf("PDF", "JPEG", "PNG", "DOCX"))
        btn_apply_filter.setOnClickListener {
            onItemClick(selectedItem)
            dismiss()
        }
    }

    private fun applyCourseChips(
        sizesOpts: Array<String>
    ) {
        course_chip_parent.removeAllViews()


        for (i in sizesOpts.indices) {
            val chip =
                layoutInflater.inflate(R.layout.chip_single, course_chip_parent, false) as Chip
            chip.text = sizesOpts[i]
            chip.setOnClickListener { selectedItem = sizesOpts[i] }
            course_chip_parent.addView(chip)
        }
    }

    private fun applyFileTypeChips(
        sizesOpts: Array<String>,
    ) {
        file_type_chip_parent.removeAllViews()
        for (i in sizesOpts.indices) {
            val chip =
                layoutInflater.inflate(R.layout.chip_single, file_type_chip_parent, false) as Chip
            chip.text = sizesOpts[i]
            chip.setOnClickListener { selectedItem = sizesOpts[i] }
            file_type_chip_parent.addView(chip)
        }
    }

    companion object {
        const val TASK_FILTER_STUDENT_MODAL = "task_filter_student_modal"

        @JvmStatic
        fun newInstance() =
            TaskFilterStudentModalFragment()
    }
}