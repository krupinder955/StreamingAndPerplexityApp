package com.olaandroidassignment.perplexity.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olaandroidassignment.R
import com.olaandroidassignment.perplexity.repositories.PerplexityRepository

class PerplexityViewmodel(private val application: Application):AndroidViewModel(application) {

    private val perplexityRepository=PerplexityRepository()

    private val perplexityMutableLiveData = MutableLiveData<String>()
    val perplexityLiveData: LiveData<String> get() = perplexityMutableLiveData

    private val errorMutableLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = errorMutableLiveData

    /**
     * This method takes as input a string from a user
     * it sets a error in errorLiveData if input is empty otherwise
     * it splits the input by comma and checks if each comma separated value is
     * a valid double between 0 & 1 if it is not it sets a error in errorLiveData
     * otherwise it calculates the perplexity and sets he value in perplexityLiveData
     */
    fun calculatePerplexity(input: String) {
        if(input.trim().isEmpty())
            errorMutableLiveData.value=application.applicationContext.getString(R.string.perplexity_will_be_displayed_here)
        else {
            var isValid=true
            val probabilities= ArrayList<Double>()
            for(word in input.split(",")){
                if(!isDouble(word)){
                    errorMutableLiveData.value= application.applicationContext.getString(R.string.entered_value_should_be_between_0_and_1_and_should_be_comma_seperated)
                    isValid=false
                    break
                }else
                    probabilities.add(word.toDouble())
            }
            if(isValid) {
                val perplexity = String.format(application.applicationContext.getString(R.string._4f),perplexityRepository.calculatePerplexity(probabilities))
                perplexityMutableLiveData.value = perplexity
            }
        }
    }

    /**
     * This method accepts a string and
     * returns true if the string is a double value and
     * lies between 0 and 1
     * otherwise it returns false
     */
    private fun isDouble(str: String?): Boolean {
        if (str.isNullOrEmpty()) {
            return false
        }
        try {
            val double =str.toDouble()
            return double>0&&double<1
        } catch (e: NumberFormatException) {
            return false
        }
    }


}