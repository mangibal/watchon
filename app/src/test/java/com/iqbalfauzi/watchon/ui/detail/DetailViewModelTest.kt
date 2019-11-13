package com.iqbalfauzi.watchon.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.iqbalfauzi.watchon.FakeData
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.data.ItemEntity
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.repository.ItemListEntity
import com.iqbalfauzi.watchon.ui.movie.MovieViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Iqbal Fauzi on 14:38 24/10/19
 */
class DetailViewModelTest {

    @get:Rule
    var instanTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel : DetailViewModel? = null
    private val data = Mockito.mock(DataRepository::class.java)

    @Before
    fun setUp() {
        viewModel = DetailViewModel(data)
    }

    @Test
    fun getMovieDetail() {
        val movies = MutableLiveData<ItemListEntity>()
        movies.value = FakeData.getDummyMovies()[0]
        Mockito.`when`(data.getMovieDetail(movies.value!!.id.toString())).thenReturn(movies)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getMovieDetail(movies.value!!.id.toString())?.observeForever(observer as Observer<ItemListEntity>)
        Mockito.verify(data).getMovieDetail(movies.value!!.id.toString())
        assertEquals(movies.value!!.posterPath, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.posterPath)
        assertEquals(movies.value!!.overview, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.overview)
        assertEquals(movies.value!!.id, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.id)
        assertEquals(movies.value!!.originalTitle, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.originalTitle)
        assertEquals(movies.value!!.originalLanguage, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.originalLanguage)
        assertEquals(movies.value!!.title, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.title)
        assertEquals(movies.value!!.backdropPath, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.backdropPath)
        assertEquals(movies.value!!.voteAverage, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.voteAverage)
        assertEquals(movies.value!!.releaseDate, viewModel?.getMovieDetail(movies.value!!.id.toString())?.value?.releaseDate)
    }

    @Test
    fun getTvDetail() {
        val tvShow = MutableLiveData<ItemListEntity>()
        tvShow.value = FakeData.getDummyMovies()[0]
        Mockito.`when`(data.getTvShowDetail(tvShow.value!!.id.toString())).thenReturn(tvShow)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getTvDetail(tvShow.value!!.id.toString())?.observeForever(observer as Observer<ItemListEntity>)
        Mockito.verify(data).getTvShowDetail(tvShow.value!!.id.toString())
        assertEquals(tvShow.value!!.posterPath, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.posterPath)
        assertEquals(tvShow.value!!.overview, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.overview)
        assertEquals(tvShow.value!!.id, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.id)
        assertEquals(tvShow.value!!.name, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.name)
        assertEquals(tvShow.value!!.originalLanguage, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.originalLanguage)
        assertEquals(tvShow.value!!.title, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.title)
        assertEquals(tvShow.value!!.backdropPath, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.backdropPath)
        assertEquals(tvShow.value!!.voteAverage, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.voteAverage)
        assertEquals(tvShow.value!!.firstAirDate, viewModel?.getTvDetail(tvShow.value!!.id.toString())?.value?.firstAirDate)
    }

    @Test
    fun getItems() {
        val movies = viewModel?.dataMovies
        val tv = viewModel?.dataTv
        assertNotNull(movies)
        assertNotNull(tv)
        assertEquals(10, movies?.size)
        assertEquals(10, tv?.size)
    }
}