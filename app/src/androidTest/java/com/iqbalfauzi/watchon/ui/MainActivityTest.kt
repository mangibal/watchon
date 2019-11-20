package com.iqbalfauzi.watchon.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.utils.EspressoIdlingResource
import com.iqbalfauzi.watchon.utils.EspressoIdlingResourceJava
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Iqbal Fauzi on 13:05 14/11/19
 */
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResourceJava.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResourceJava.getEspressoIdlingResource())
    }

    @Test
    fun testAppBehaviour() {
        //Visibility Check
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        pressBack()
        onView(withId(R.id.navigation_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

}