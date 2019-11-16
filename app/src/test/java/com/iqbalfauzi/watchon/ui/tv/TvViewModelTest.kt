package com.iqbalfauzi.watchon.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.iqbalfauzi.watchon.FakeJson
import com.iqbalfauzi.watchon.data.model.DataResponse
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Iqbal Fauzi on 10:19 24/10/19
 */
class TvViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : TvViewModel
    @Mock
    private lateinit var dataRepository : DataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvViewModel(dataRepository)
    }

    @Test
    fun getTvShows() {
        val fakeTvShows = Gson().fromJson(FakeJson.jsonTvShow, DataResponse::class.java)
        val response = MutableLiveData<List<ResultEntity>>()
        response.value = fakeTvShows.results

        whenever(dataRepository.getTvShows()).thenReturn(response)

        val observer = mock<Observer<List<ResultEntity>>>()
        viewModel.getTvShows().observeForever(observer)

        verify(observer).onChanged(fakeTvShows.results)
    }
}