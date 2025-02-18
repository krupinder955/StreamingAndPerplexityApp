package com.olaandroidassignment.streaming.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.olaandroidassignment.R
import com.olaandroidassignment.streaming.adapters.StreamingAdapter
import com.olaandroidassignment.databinding.ActivityStreamingBinding
import com.olaandroidassignment.streaming.models.StreamingData
import com.olaandroidassignment.streaming.util.NetworkUtils
import com.olaandroidassignment.streaming.viewmodels.StreamingViewModel

class StreamingActivity : AppCompatActivity() {

    private lateinit var adapter: StreamingAdapter
    private lateinit var binding: ActivityStreamingBinding
    private lateinit var viewModel:StreamingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStreamingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeMemberVariables()
        setListeners()
        setupRecyclerView()
        observeLiveData()
        startWebSocket()
    }

    /**
     * This method is used to set listeners on views
     */
    private fun setListeners() {
        binding.aivBack.setOnClickListener {
            //finishing the activity when back button is pressed
            finish()
        }
        binding.btnRetry.setOnClickListener {
            if(NetworkUtils.isNetworkAvailable(this))
            {
                binding.btnRetry.visibility=View.GONE
                viewModel.startWebSocketConnection()
            }
        }
    }

    /**
     * This method is used to initialize member variables
     */
    private fun initializeMemberVariables() {
        viewModel= ViewModelProvider(this)[StreamingViewModel::class.java]
        adapter= StreamingAdapter(this,viewModel.list)
    }

    /**
     * This method is used to setup recycler view
     */
    private fun setupRecyclerView() {
        binding.rvStreamingData.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvStreamingData.adapter=adapter
    }

    /**
     * This method is used to set observers on livedata in viewmodel
     */
    private fun observeLiveData() {
        //observing streamingLiveData
        //call comes in this whenever any new data is received
        viewModel.streamingLiveData.observe(this) {
            //notifying the adapter that new data is inserted at position 0
            adapter.notifyItemInserted(0)
        }
    }

    /**
     * This method checks if there is internet connection
     * it connects the web socket else show a retry button
     */
    private fun startWebSocket() {
        if(NetworkUtils.isNetworkAvailable(this))
            viewModel.startWebSocketConnection()
        else
            binding.btnRetry.visibility= View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        //closing web socket connection whenever activity is destroyed
        viewModel.closeConnection()
    }

}