package com.iqbalfauzi.watchon.utils

/**
 * Created by Iqbal Fauzi on 17:06 16/10/19
 */
object Utils {
    fun nomalizeRating(oldValue: Float): Float{
        return ((oldValue-0)/10-0)*((5-0)+0)
    }
}
