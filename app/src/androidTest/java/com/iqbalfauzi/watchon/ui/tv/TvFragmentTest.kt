package com.iqbalfauzi.watchon.ui.tv

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
 * Created by Iqbal Fauzi on 12:33 28/10/19
 */
class TvFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvFragment = TvFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvFragment)
    }

    @Test
    fun loadTvItems() {
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).check(object : RecyclerViewItemCountAssertion(10) {

        })
    }

}