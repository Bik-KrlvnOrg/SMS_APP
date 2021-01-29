package com.cheise_proj.core.feature.student.account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.account.vo.AccountItem
import kotlinx.android.synthetic.main.list_account_items.view.*

class AccountAdapter(private val onItemClick: (AccountItem?) -> Unit = {}) :
    ListAdapter<AccountItem, AccountAdapter.AccountVh>(AccountDiff()) {
    class AccountVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AccountItem?, onItemClick: (AccountItem?) -> Unit) {
            with(itemView) {
                this.setOnClickListener { onItemClick(item) }
                logo.setImageDrawable(ContextCompat.getDrawable(context, item?.image!!))
                header.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountVh {
        return AccountVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_account_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountVh, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

internal class AccountDiff : DiffUtil.ItemCallback<AccountItem>() {
    override fun areItemsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
        return oldItem == newItem
    }
}