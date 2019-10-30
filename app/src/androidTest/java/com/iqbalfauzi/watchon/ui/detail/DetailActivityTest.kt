package com.iqbalfauzi.watchon.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.utils.FakeDataDummy
import org.junit.Rule
import org.junit.Test


/**
 * Created by Iqbal Fauzi on 12:51 28/10/19
 */
class DetailActivityTest {

    private val dummyMovie = FakeDataDummy.getDataMovies()[0]
    private val dummyTv = FakeDataDummy.getDataTv()[0]

    /*
    * Set movie if will test movie and tv if will test tv on putExtra
    * Call dummyTv if will test tv item
    * */

    @get:Rule
    var activityRule: ActivityTestRule<DetailActivity> = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.TYPE, "movie")
            result.putExtra(DetailActivity.ITEM_ID, dummyMovie.id.toString())
            return result
        }
    }

    @Test
    fun loadItem() {
        //cek isDisplayed
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date_constraint)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_constraint)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))
        onView(withId(R.id.tv_date)).check(matches(withText(dummyMovie.date)))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyMovie.score.toString())))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie.overview)))
    }
}