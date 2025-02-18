package com.olaandroidassignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olaandroidassignment.databinding.ActivityMainBinding
import com.olaandroidassignment.perplexity.activities.PerplexityActivity
import com.olaandroidassignment.streaming.activities.StreamingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    /**
     * This method is used to set listeners on the views
     */
    private fun setListeners() {
        binding.btnStreaming.setOnClickListener {
            //opening streaming activity
            startActivity(Intent(this,StreamingActivity::class.java))
        }
        binding.btnPerplexityMathModel.setOnClickListener {
            //opening perplexity activity
            startActivity(Intent(this,PerplexityActivity::class.java))
        }
    }
}