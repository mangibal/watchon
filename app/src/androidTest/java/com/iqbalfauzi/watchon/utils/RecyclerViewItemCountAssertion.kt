package com.iqbalfauzi.watchon.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.Matcher
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertNotNull


/**
 * Created by Iqbal Fauzi on 11:06 28/10/19
 */
class RecyclerViewItemCountAssertion(private val matcher: Matcher<Int>) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter?.itemCount, matcher)
    }

    companion object {

        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
            return withItemCount(`is`(expectedCount))
        }

        private fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(matcher)
        }
    }
}