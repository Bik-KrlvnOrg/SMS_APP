package com.cheise_proj.core.feature.student.ui.account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.ui.account.model.AccountMenu
import kotlinx.android.synthetic.main.list_item_account.view.*

class AccountMenuAdapter constructor(private val onItemClick: (AccountMenu?) -> Unit = {}) :
    ListAdapter<AccountMenu, AccountMenuAdapter.AccountMenuVh>(AccountMenuDiff()) {
    class AccountMenuVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AccountMenu?, onItemClick: (AccountMenu?) -> Unit) {
            with(itemView) {
                item_header.text = item?.title
                setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountMenuVh {
        return AccountMenuVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_account, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountMenuVh, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

internal class AccountMenuDiff : DiffUtil.ItemCallback<AccountMenu>() {
    override fun areItemsTheSame(oldItem: AccountMenu, newItem: AccountMenu): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AccountMenu, newItem: AccountMenu): Boolean {
        return oldItem == newItem
    }
}