package com.iqbalfauzi.watchon.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.iqbalfauzi.watchon.FakeData
import com.iqbalfauzi.watchon.FakeJson
import com.iqbalfauzi.watchon.LiveDataTest
import com.iqbalfauzi.watchon.data.model.ItemListEntity
import com.iqbalfauzi.watchon.data.model.DataResponse
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.iqbalfauzi.watchon.data.repository.local.LocalRepository
import com.iqbalfauzi.watchon.data.repository.remote.RemoteRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Iqbal Fauzi on 13:53 10/11/19
 */
class DataRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var localRepository: LocalRepository
    @Mock
    private lateinit var remoteRepository : RemoteRepository
    private lateinit var dataRepositoryTest : FakeDataRepository

    private val fakeMovieResponse = Gson().fromJson(FakeJson.jsonMovies, DataResponse::class.java)
    private val fakeTvShowResponse = Gson().fromJson(FakeJson.jsonTvShow, DataResponse::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataRepositoryTest = FakeDataRepository(localRepository, remoteRepository)
    }

    @Test
    fun getMovies() {
        doAnswer {  invocation ->
            (invocation.arguments[0] as RemoteRepository.GetMovieCallback).onSuccess(fakeMovieResponse.results)
            null
        }.whenever(remoteRepository).getMovies(any())

        val result = LiveDataTest.getValue(dataRepositoryTest.getMovies())
        verify(remoteRepository, times(1)).getMovies(any())

        assertNotNull(result)
        assertEquals(fakeMovieResponse.results.size, result.size)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.GetTvShowsCallback).onSuccess(fakeTvShowResponse.results)
            null
        }.whenever(remoteRepository).getTvShows(any())

        val result = LiveDataTest.getValue(dataRepositoryTest.getTvShows())
        verify(remoteRepository, times(1)).getTvShows(any())

        assertNotNull(result)
        assertEquals(fakeTvShowResponse.results.size, result.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.GetMovieDetailCallback).onSuccess(fakeMovieResponse.results[0])
            null
        }.`when`(remoteRepository).getMovieDetail(eq(fakeMovieResponse.results[0].id.toString()), any())
    }

    @Test
    fun getTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.GetTvShowDetailCallback).onSuccess(fakeTvShowResponse.results[0])
            null
        }.`when`(remoteRepository).getMovieDetail(eq(fakeTvShowResponse.results[0].id.toString()), any())
    }
}