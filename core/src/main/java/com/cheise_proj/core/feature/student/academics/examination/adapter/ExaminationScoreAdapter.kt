package com.cheise_proj.core.feature.student.academics.examination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.core.R
import com.cheise_proj.core.feature.student.academics.examination.vo.ExaminationScore
import kotlinx.android.synthetic.main.list_examination_score_items.view.*

class ExaminationScoreAdapter(private val onItemClick: (ExaminationScore?) -> Unit = {}) :
    ListAdapter<ExaminationScore, ExaminationScoreAdapter.ExaminationScoreVh>(ExaminationScoreDiff()) {
    class ExaminationScoreVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ExaminationScore?, onItemClick: (ExaminationScore?) -> Unit) {
            with(itemView) {
                this.setOnClickListener { onItemClick(item) }
                item_1.text = item?.subject
                item_2.text = item?.marks
                item_3.text = item?.total
                item_4.text = item?.grade
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExaminationScoreVh {
        return ExaminationScoreVh(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_examination_score_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExaminationScoreVh, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

internal class ExaminationScoreDiff : DiffUtil.ItemCallback<ExaminationScore>() {
    override fun areItemsTheSame(oldItem: ExaminationScore, newItem: ExaminationScore): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExaminationScore, newItem: ExaminationScore): Boolean {
        return oldItem == newItem
    }
}