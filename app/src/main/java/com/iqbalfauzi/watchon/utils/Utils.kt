package com.iqbalfauzi.watchon.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

/**
 * Created by Iqbal Fauzi on 17:06 16/10/19
 */
object Utils {
    fun nomalizeRating(oldValue: Float): Float{
        return ((oldValue-0)/10-0)*((5-0)+0)
    }
    fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 4f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}
