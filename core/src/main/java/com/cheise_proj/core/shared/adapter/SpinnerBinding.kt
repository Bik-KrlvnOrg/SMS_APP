package com.cheise_proj.core.shared.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
fun bindSpinnerData(
    spinner: Spinner,
    newSelectedValue: String,
    newTextAttrChanged: InverseBindingListener
) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View,
            position: Int,
            id: Long
        ) {
            newTextAttrChanged.onChange()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    if (newSelectedValue.isEmpty()) return
    val pos =
        (spinner.adapter as ArrayAdapter<String?>).getPosition(
            newSelectedValue
        )
    spinner.setSelection(pos, true)
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun captureSelectedValue(spinner: AppCompatSpinner): String {
    return spinner.selectedItem as String
}
