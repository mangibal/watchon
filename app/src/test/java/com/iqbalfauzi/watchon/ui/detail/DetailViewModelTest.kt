package com.iqbalfauzi.watchon.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.iqbalfauzi.watchon.FakeData
import com.iqbalfauzi.watchon.FakeJson
import com.iqbalfauzi.watchon.data.model.DataResponse
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.model.ItemListEntity
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Iqbal Fauzi on 14:38 24/10/19
 */
class DetailViewModelTest {

    @get:Rule
    var instanTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel : DetailViewModel? = null
    @Mock
    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovieDetail() {
        val fakeMovies = Gson().fromJson(FakeJson.jsonMovies, DataResponse::class.java)
        val fakeMovie = MutableLiveData<ResultEntity>()
        fakeMovie.value = fakeMovies.results[0]
        val movieId = fakeMovies.results[0].id

        whenever(dataRepository.getMovieDetail(movieId.toString())).thenReturn(fakeMovie)

        val observer = mock<Observer<ResultEntity>>()
        viewModel?.getMovieDetail(movieId.toString())?.observeForever(observer)

        verify(observer).onChanged(fakeMovie.value)
        assertEquals(fakeMovies.results[0].id, viewModel?.getMovieDetail(movieId.toString())?.value?.id)
        assertEquals(fakeMovies.results[0].adult, viewModel?.getMovieDetail(movieId.toString())?.value?.adult)
        assertEquals(fakeMovies.results[0].backdrop_path, viewModel?.getMovieDetail(movieId.toString())?.value?.backdrop_path)
        assertEquals(fakeMovies.results[0].original_language, viewModel?.getMovieDetail(movieId.toString())?.value?.original_language)
        assertEquals(fakeMovies.results[0].original_title, viewModel?.getMovieDetail(movieId.toString())?.value?.original_title)
        assertEquals(fakeMovies.results[0].overview, viewModel?.getMovieDetail(movieId.toString())?.value?.overview)
        assertEquals(fakeMovies.results[0].popularity, viewModel?.getMovieDetail(movieId.toString())?.value?.popularity)
        assertEquals(fakeMovies.results[0].genre_ids, viewModel?.getMovieDetail(movieId.toString())?.value?.genre_ids)
        assertEquals(fakeMovies.results[0].poster_path, viewModel?.getMovieDetail(movieId.toString())?.value?.poster_path)
        assertEquals(fakeMovies.results[0].release_date, viewModel?.getMovieDetail(movieId.toString())?.value?.release_date)
        assertEquals(fakeMovies.results[0].title, viewModel?.getMovieDetail(movieId.toString())?.value?.title)
        assertEquals(fakeMovies.results[0].vote_average, viewModel?.getMovieDetail(movieId.toString())?.value?.vote_average)
        assertEquals(fakeMovies.results[0].vote_count, viewModel?.getMovieDetail(movieId.toString())?.value?.vote_count)
    }

    @Test
    fun getTvShowDetail() {
        val fakeTvShows = Gson().fromJson(FakeJson.jsonTvShow, DataResponse::class.java)
        val fakeTvShow = MutableLiveData<ResultEntity>()
        fakeTvShow.value = fakeTvShows.results[0]
        val movieId = fakeTvShows.results[0].id

        whenever(dataRepository.getMovieDetail(movieId.toString())).thenReturn(fakeTvShow)

        val observer = mock<Observer<ResultEntity>>()
        viewModel?.getMovieDetail(movieId.toString())?.observeForever(observer)

        verify(observer).onChanged(fakeTvShow.value)
        assertEquals(fakeTvShows.results[0].id, viewModel?.getMovieDetail(movieId.toString())?.value?.id)
        assertEquals(fakeTvShows.results[0].adult, viewModel?.getMovieDetail(movieId.toString())?.value?.adult)
        assertEquals(fakeTvShows.results[0].backdrop_path, viewModel?.getMovieDetail(movieId.toString())?.value?.backdrop_path)
        assertEquals(fakeTvShows.results[0].original_language, viewModel?.getMovieDetail(movieId.toString())?.value?.original_language)
        assertEquals(fakeTvShows.results[0].original_name, viewModel?.getMovieDetail(movieId.toString())?.value?.original_name)
        assertEquals(fakeTvShows.results[0].overview, viewModel?.getMovieDetail(movieId.toString())?.value?.overview)
        assertEquals(fakeTvShows.results[0].popularity, viewModel?.getMovieDetail(movieId.toString())?.value?.popularity)
        assertEquals(fakeTvShows.results[0].genre_ids, viewModel?.getMovieDetail(movieId.toString())?.value?.genre_ids)
        assertEquals(fakeTvShows.results[0].poster_path, viewModel?.getMovieDetail(movieId.toString())?.value?.poster_path)
        assertEquals(fakeTvShows.results[0].first_air_date, viewModel?.getMovieDetail(movieId.toString())?.value?.first_air_date)
        assertEquals(fakeTvShows.results[0].name, viewModel?.getMovieDetail(movieId.toString())?.value?.name)
        assertEquals(fakeTvShows.results[0].vote_average, viewModel?.getMovieDetail(movieId.toString())?.value?.vote_average)
        assertEquals(fakeTvShows.results[0].vote_count, viewModel?.getMovieDetail(movieId.toString())?.value?.vote_count)
    }

     /*@Test
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
     }*/
}