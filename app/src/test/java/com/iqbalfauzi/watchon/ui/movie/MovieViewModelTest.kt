package com.iqbalfauzi.watchon.ui.movie

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Iqbal Fauzi on 10:17 24/10/19
 */
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }

}