package com.cheise_proj.core.feature.student.ui.fees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.domain.model.FeesPaid
import kotlinx.android.synthetic.main.fees_paid_item.view.*
import kotlinx.android.synthetic.main.item_content.view.*

class FeesPaidAdapter :
    ListAdapter<FeesPaid, FeesPaidAdapter.FeesPaidVh>(FeesPaidDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeesPaidVh {
        return FeesPaidVh(
            LayoutInflater.from(parent.context).inflate(R.layout.fees_paid_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FeesPaidVh, position: Int) {
        holder.bind(getItem(position))
    }

    class FeesPaidVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: FeesPaid?) {
            with(itemView) {
                header.text = "student name here: ${item?.studentId}"
                subtitle.text = "class name here: ${item?.classId}"
                val content1 = LayoutInflater.from(context)
                    .inflate(R.layout.item_content, container, false)
                content1.item_header.text = "Fees Type"
                content1.item_subtitle.text = item?.particularName

                val content2 = LayoutInflater.from(context)
                    .inflate(R.layout.item_content, container, false)
                content2.item_header.text = "Date"
                content2.item_subtitle.text = item?.createdDate

                val content3 = LayoutInflater.from(context)
                    .inflate(R.layout.item_content, container, false)
                content3.item_header.text = "Amount"
                content3.item_subtitle.text = "${item?.feeAmount}"

                container.removeAllViews()
                container.addView(content1)
                container.addView(content2)
                container.addView(content3)
            }
        }
    }
}

internal class FeesPaidDiff : DiffUtil.ItemCallback<FeesPaid>() {
    override fun areItemsTheSame(oldItem: FeesPaid, newItem: FeesPaid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FeesPaid, newItem: FeesPaid): Boolean {
        return oldItem == newItem
    }
}