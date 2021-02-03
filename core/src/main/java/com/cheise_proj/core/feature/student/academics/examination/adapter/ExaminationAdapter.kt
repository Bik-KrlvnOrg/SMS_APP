package com.cheise_proj.core.feature.student.academics.examination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.academics.examination.vo.ExaminationItem
import kotlinx.android.synthetic.main.list_examination_items.view.*

class ExaminationAdapter(private val onItemClick: (ExaminationItem?) -> Unit = {}) :
    ListAdapter<ExaminationItem, ExaminationAdapter.ExaminationVh>(ExaminationDiff()) {
    class ExaminationVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ExaminationItem?, onItemClick: (ExaminationItem?) -> Unit) {
            with(itemView) {
                this.setOnClickListener { onItemClick(item) }
                header.text = item?.title
                sub_header.text = item?.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExaminationVh {
        return ExaminationVh(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_examination_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExaminationVh, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

internal class ExaminationDiff : DiffUtil.ItemCallback<ExaminationItem>() {
    override fun areItemsTheSame(oldItem: ExaminationItem, newItem: ExaminationItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExaminationItem, newItem: ExaminationItem): Boolean {
        return oldItem == newItem
    }
}