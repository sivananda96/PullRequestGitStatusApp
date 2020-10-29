package com.example.pullrequestgitstatusapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.pullrequestgitstatusapp.data.models.PullRequest
import com.example.pullrequestgitstatusapp.databinding.ItemPrListBinding
import com.example.pullrequestgitstatusapp.utils.GeneralUtils

class PRItemListAdapter constructor(private val onItemClicked: (String?) -> Unit)
    : PagedListAdapter<PullRequest, PRItemListAdapter.ViewHolder>(PRItemDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            if (item != null) {
                bind(item, onItemClicked)
                itemView.tag = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPrListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    class ViewHolder(private val binding: ItemPrListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(curItem: PullRequest, onItemClicked: (String?) -> Unit) {
            binding.apply {
                root.setOnClickListener { onItemClicked(curItem.html_url) }
                item = curItem
                //prItemTags.addTags(GeneralUtils.generateTagListFromLabels(curItem.labels))
                executePendingBindings()
            }
        }
    }
}