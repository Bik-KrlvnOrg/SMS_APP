package com.cheise_proj.core.feature.student.notification.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.notification.vo.NotificationItem
import kotlinx.android.synthetic.main.list_notification_msg_items.view.*
import java.time.format.DateTimeFormatter

class NotificationAdapter(private val onItemClick: (NotificationItem?) -> Unit = {}) :
    ListAdapter<NotificationItem, NotificationAdapter.NotificationVh>(NotificationDiff()) {
    class NotificationVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: NotificationItem?, onItemClick: (NotificationItem?) -> Unit) {
            with(itemView) {
                header.text = item?.name
                sub_header.text = item?.body
                sub_text.text = item?.dateTime?.format(DateTimeFormatter.ofPattern("hh:mm"))
                item?.document?.let {
                    item_1.text = it
                } ?: run { item_1.visibility = View.GONE }
                this.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationVh {
        return NotificationVh(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_notification_msg_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotificationVh, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

internal class NotificationDiff : DiffUtil.ItemCallback<NotificationItem>() {
    override fun areItemsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean {
        return oldItem == newItem
    }
}