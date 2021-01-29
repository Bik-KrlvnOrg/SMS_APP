package com.cheise_proj.core.feature.student.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.dashboard.vo.DashboardItem
import kotlinx.android.synthetic.main.list_dashboard_items.view.*

class DashboardAdapter(private val onItemClick: (DashboardItem?) -> Unit = {}) :
    ListAdapter<DashboardItem, DashboardAdapter.DashVh>(DashDiff()) {
    class DashVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DashboardItem?, onItemClick: (DashboardItem?) -> Unit) {
            with(itemView) {
                this.setOnClickListener { onItemClick(item) }
                logo.setImageDrawable(ContextCompat.getDrawable(context, item?.image!!))
                header.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashVh {
        return DashVh(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_dashboard_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DashVh, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

internal class DashDiff : DiffUtil.ItemCallback<DashboardItem>() {
    override fun areItemsTheSame(oldItem: DashboardItem, newItem: DashboardItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DashboardItem, newItem: DashboardItem): Boolean {
        return oldItem == newItem
    }
}

