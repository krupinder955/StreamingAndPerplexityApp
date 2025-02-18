package com.olaandroidassignment.perplexity.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.olaandroidassignment.R
import com.olaandroidassignment.databinding.ActivityPerplexityBinding
import com.olaandroidassignment.perplexity.viewmodels.PerplexityViewmodel

class PerplexityActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPerplexityBinding
    private lateinit var viewmodel: PerplexityViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPerplexityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeMemberVariables()
        setListeners()
        setObservers()
    }

    private fun setObservers() {

        //observing perplexity livedata
        //call comes in this whenever user enters valid input i.e comma seperated
        //values between 0 & 1 and perplexity is calculated successfully
        viewmodel.perplexityLiveData.observe(this) {
            binding.atvPerplexity.text = getString(R.string.perplexity_is, it)
        }

        //observing errorLiveData
        //call comes in this whenever there is some error
        //input box is empty or entered input by user is not
        //comma separated values between 0 & 1
        viewmodel.errorLiveData.observe(this) {
            binding.atvPerplexity.text = it
        }

    }

    /**
     * This method is used to initialize member variables of this class
     */
    private fun initializeMemberVariables() {
        viewmodel= ViewModelProvider(this)[PerplexityViewmodel::class.java]
    }

    /**
     * This function is used to set listeners to the views
     */
    private fun setListeners() {

        binding.aivBack.setOnClickListener {
            //finishing the activity when back button is pressed
            finish()
        }

        binding.aetProbabilities.addTextChangedListener(object:TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                //when ever user enters something in the text box
                //calculating the perplexity
                viewmodel.calculatePerplexity(editable.toString())
            }

        })
    }
}