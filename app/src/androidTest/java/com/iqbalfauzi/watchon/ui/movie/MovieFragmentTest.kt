package com.iqbalfauzi.watchon.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.testing.SingleFragmentActivity
import com.iqbalfauzi.watchon.utils.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Iqbal Fauzi on 11:16 28/10/19
 */
class MovieFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val movieFragment = MovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(movieFragment)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(object : RecyclerViewItemCountAssertion(10) {
        })
    }
}