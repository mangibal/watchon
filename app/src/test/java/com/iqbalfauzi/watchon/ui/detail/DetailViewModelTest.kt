package com.iqbalfauzi.watchon.ui.detail

import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.data.ItemEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Iqbal Fauzi on 14:38 24/10/19
 */
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private var movieEntity = ItemEntity()
    private var tvEntity = ItemEntity()

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        movieEntity = ItemEntity(
            1,
            "A Star Is Born",
            "2018-10-03",
            75,
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            R.drawable.poster_a_start_is_born
        )
        tvEntity = ItemEntity(
            1,
            "Arrow",
            "2012-10-10",
            58,
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            R.drawable.poster_arrow
        )
    }

    @Test
    fun getMovieDetail() {
        viewModel.itemId = movieEntity.id.toString()
        val movieDetail = viewModel.getMovieDetail()
        assertNotNull(movieDetail)
        assertEquals(movieEntity.id, movieDetail.id)
        assertEquals(movieEntity.title, movieDetail.title)
        assertEquals(movieEntity.date, movieDetail.date)
        assertEquals(movieEntity.score, movieDetail.score)
        assertEquals(movieEntity.overview, movieDetail.overview)
    }

    @Test
    fun getTvDetail() {
        viewModel.itemId = movieEntity.id.toString()
        val tvDetail = viewModel.getTvDetail()
        assertNotNull(tvDetail)
        assertEquals(tvEntity.id, tvDetail.id)
        assertEquals(tvEntity.title, tvDetail.title)
        assertEquals(tvEntity.date, tvDetail.date)
        assertEquals(tvEntity.score, tvDetail.score)
        assertEquals(tvEntity.overview, tvDetail.overview)
    }

    @Test
    fun getItems() {
        val movies = viewModel.dataMovies
        val tv = viewModel.dataTv
        assertNotNull(movies)
        assertNotNull(tv)
        assertEquals(10, movies.size)
        assertEquals(10, tv.size)
    }
}