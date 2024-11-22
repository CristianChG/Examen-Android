package com.example.myapplication.framework.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.network.model.HistoricalEvent
import com.example.myapplication.databinding.ItemHistoricalEventBinding

class HistoricalEventViewHolder(private val binding: ItemHistoricalEventBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(event: HistoricalEvent) {
        binding.tvDate.text = event.date
        binding.tvDescription.text = event.description
        binding.tvCategory1.text = event.category1
        binding.tvCategory2.text = event.category2
    }
}
