package com.example.pullrequestgitstatusapp.ui.adapter


import androidx.recyclerview.widget.DiffUtil
import com.example.pullrequestgitstatusapp.data.models.PullRequest

class PRItemDiffCallback : DiffUtil.ItemCallback<PullRequest>() {
    override fun areItemsTheSame(item1: PullRequest, item2: PullRequest): Boolean {
        return item1 == item2
    }

    override fun areContentsTheSame(item1: PullRequest, item2: PullRequest): Boolean {
        return item1.id == item2.id
    }
}