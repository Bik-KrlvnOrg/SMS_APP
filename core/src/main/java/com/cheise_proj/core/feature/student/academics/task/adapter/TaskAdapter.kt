package com.cheise_proj.core.feature.student.academics.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.academics.task.vo.TaskItem
import kotlinx.android.synthetic.main.list_task_items.view.*

class TaskAdapter(private val onItemClick: (TaskItem?) -> Unit = {}) :
    ListAdapter<TaskItem, TaskAdapter.TaskVh>(TaskDiff()) {
    class TaskVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TaskItem?, onItemClick: (TaskItem?) -> Unit) {
            with(itemView) {
                this.setOnClickListener { onItemClick(item) }
                header.text = item?.title
                sub_header.text = item?.subTitle
                item_1.text = item?.date
                item_2.text = item?.status
                logo.setImageDrawable(ContextCompat.getDrawable(context, item?.image!!))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVh {
        return TaskVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_task_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskVh, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

internal class TaskDiff : DiffUtil.ItemCallback<TaskItem>() {
    override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem == newItem
    }
}