package com.speerthomas.fetchrewardscodingexercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.speerthomas.fetchrewardscodingexercise.databinding.ItemFetchRewardsBinding
import com.speerthomas.fetchrewardscodingexercise.model.FetchRewardsItem

class FetchRewardsAdapter(
    private val itemClicked:(FetchRewardsItem) -> Unit
): RecyclerView.Adapter<FetchRewardsAdapter.FetchRewardsViewHolder>() {
    private var itemList: List<FetchRewardsItem> = emptyList()

    fun bindData(items: List<FetchRewardsItem>){
        itemList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchRewardsViewHolder {
        val binding = ItemFetchRewardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FetchRewardsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FetchRewardsViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class FetchRewardsViewHolder(
        val binding: ItemFetchRewardsBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: FetchRewardsItem){
            binding.listId.text = "List ID: ${item.listId}"
            binding.name.text = "Name: ${item.name}"

            itemView.setOnClickListener {
                itemClicked(item)
            }
        }
    }
}