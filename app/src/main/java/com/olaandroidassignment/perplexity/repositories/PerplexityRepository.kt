package com.olaandroidassignment.perplexity.repositories

import kotlin.math.log2
import kotlin.math.pow

class PerplexityRepository {

    /**
     * This method accepts list of probabilities and
     * returns the calculated perplexity
     */
    fun calculatePerplexity(probabilities: List<Double>):Double {

        var entropySum = 0.0
        for (p in probabilities) {
            entropySum += (p * log2(p))
        }

        val perplexity = 2.0.pow(-entropySum)

        return perplexity

    }
}