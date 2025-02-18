package com.olaandroidassignment.streaming.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olaandroidassignment.R
import com.olaandroidassignment.databinding.ItemStreamingBinding
import com.olaandroidassignment.streaming.models.StreamingData

/**
 * This is a adapter class for recycler view in StreamingActivity
 */
class StreamingAdapter(val context:Context,private val list:List<StreamingData>) :RecyclerView.Adapter<StreamingAdapter.StreamingViewHolder>(){

    inner class StreamingViewHolder(private val binding: ItemStreamingBinding): RecyclerView.ViewHolder(binding.root) {

        /**
         * This method is used to set data in recycler
         * view's item view
         * called from onBindViewHolder
         */
        fun bind(streamingData: StreamingData) {
            binding.atvType.text=streamingData.type
            binding.atvSequence.text=String.format(context.getString(R.string.d), streamingData.sequence)
            binding.atvProductId.text=streamingData.product_id
            binding.atvPrice.text=streamingData.price
            binding.atvOpen24Hours.text=streamingData.open_24h
            binding.atvVolume24Hours.text=streamingData.volume_24h
            binding.atvLow24Hours.text=streamingData.low_24h
            binding.atvHigh24Hours.text=streamingData.high_24h
            binding.atvVolume30D.text=streamingData.volume_30d
            binding.atvBestBid.text=streamingData.best_bid
            binding.atvBestBidSize.text=streamingData.best_bid_size
            binding.atvBestAsk.text=streamingData.best_ask
            binding.atvBestAskSize.text=streamingData.best_ask_size
            binding.atvSide.text=streamingData.side
            binding.atvTime.text=streamingData.time
            binding.atvTradeId.text=String.format(context.getString(R.string.d), streamingData.trade_id)
            binding.atvLastSize.text=streamingData.last_size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamingViewHolder {
        val binding = ItemStreamingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StreamingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StreamingViewHolder, position: Int) {
        holder.bind(list[position])
    }
}