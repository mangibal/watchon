package com.iqbalfauzi.watchon.ui.movie

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
import com.iqbalfauzi.watchon.data.repository.remote.RemoteRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Iqbal Fauzi on 10:17 24/10/19
 */
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : MovieViewModel
    @Mock
    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(dataRepository)
    }

    @Test
    fun getMovies() {
        val fakeMovies = Gson().fromJson(FakeJson.jsonMovies, DataResponse::class.java)
        val response = MutableLiveData<List<ResultEntity>>()
        response.value = fakeMovies.results

        whenever(dataRepository.getMovies()).thenReturn(response)

        val observer = mock<Observer<List<ResultEntity>>>()
        viewModel.getMovies().observeForever(observer)

        verify(observer).onChanged(fakeMovies.results)
    }

}