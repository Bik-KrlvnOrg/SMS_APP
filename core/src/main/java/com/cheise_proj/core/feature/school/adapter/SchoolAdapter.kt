package com.cheise_proj.core.feature.school.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.school.vo.SchoolItem
import kotlinx.android.synthetic.main.list_school_items.view.*

class SchoolAdapter(private val onClickItem: (SchoolItem?) -> Unit = {}) :
    ListAdapter<SchoolItem, SchoolAdapter.SchoolVh>(SchoolDiff()) {
    class SchoolVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SchoolItem?, onClickItem: (SchoolItem?) -> Unit) {
            with(itemView) {
                this.setOnClickListener { onClickItem(item) }
                header.text = item?.title
                sub_header.text = item?.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolVh {
        return SchoolVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_school_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SchoolVh, position: Int) {
        return holder.bind(getItem(position), onClickItem)
    }
}

internal class SchoolDiff : DiffUtil.ItemCallback<SchoolItem>() {
    override fun areItemsTheSame(oldItem: SchoolItem, newItem: SchoolItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SchoolItem, newItem: SchoolItem): Boolean {
        return oldItem == newItem
    }
}