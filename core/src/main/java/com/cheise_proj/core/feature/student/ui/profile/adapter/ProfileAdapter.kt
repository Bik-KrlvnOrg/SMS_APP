package com.cheise_proj.core.feature.student.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileAdapter :
    ListAdapter<String, ProfileAdapter.ProfileVh>(ProfileDiff()) {
    class ProfileVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String?) {
            with(itemView) {
                item_content.text = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileVh {
        return ProfileVh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileVh, position: Int) {
        holder.bind(getItem(position))
    }
}

internal class ProfileDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}