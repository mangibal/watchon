package com.iqbalfauzi.watchon.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.data.model.ItemListEntity
import com.iqbalfauzi.watchon.utils.EspressoIdlingResource
import com.iqbalfauzi.watchon.utils.EspressoIdlingResourceJava
import com.iqbalfauzi.watchon.utils.FakeDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by Iqbal Fauzi on 12:51 28/10/19
 */
class DetailActivityTest {

    private val dummyMovie = ItemListEntity(
        id = 475557,
        posterPath = "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
        backdropPath = "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
        title = "Joker",
        originalTitle = "Joker",
        originalLanguage = "en",
        voteAverage = 8.5,
        releaseDate = "2019-10-02",
        overview = "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
        firstAirDate = null,
        name = null
    )
    private val dummyTv = FakeDataDummy.getDummyTvShows()[0]

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

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResourceJava.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResourceJava.getEspressoIdlingResource())
    }

    @Test
    fun loadItem() {
        //cek isDisplayed
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title_detail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovie.originalTitle?:dummyMovie.title)))
        }

        onView(withId(R.id.tv_release_date_constraint)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_score)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_date_detail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovie.releaseDate)))
        }

        onView(withId(R.id.tv_overview_constraint)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_detail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovie.overview)))
        }

        /*onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date_constraint)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_constraint)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyMovie.title)))
        onView(withId(R.id.tv_date_detail)).check(matches(withText(dummyMovie.releaseDate?:dummyMovie.firstAirDate)))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyMovie.voteAverage.toString())))
        onView(withId(R.id.tv_overview_detail)).check(matches(withText(dummyMovie.overview)))*/
    }
}