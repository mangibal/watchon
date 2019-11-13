package com.iqbalfauzi.watchon.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.iqbalfauzi.watchon.FakeData
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.repository.ItemListEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Iqbal Fauzi on 10:17 24/10/19
 */
class MovieViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel : MovieViewModel? = null
    private val data = Mockito.mock(DataRepository::class.java)
    private lateinit var itemList: ItemListEntity

    @Before
    fun setUp() {
        viewModel = MovieViewModel(data)
        itemList = ItemListEntity(
            "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
            "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure",
            "2019-10-04",
            475557,
            "Joker",
            "en",
            "Joker",
            "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
            8.5,
            null,
            null
        )
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<ItemListEntity>>()
        movies.value = FakeData.getDummyMovies()
        Mockito.`when`(data.getMovies()).thenReturn(movies)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.movies?.observeForever(observer as Observer<List<ItemListEntity>>)
        Mockito.verify(data).getMovies()
    }

}