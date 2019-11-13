package com.iqbalfauzi.watchon.ui.tv

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
 * Created by Iqbal Fauzi on 10:19 24/10/19
 */
class TvViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel : TvViewModel? = null
    private val data = Mockito.mock(DataRepository::class.java)
    private lateinit var itemList: ItemListEntity

    @Before
    fun setUp() {
        viewModel = TvViewModel(data)
        itemList = ItemListEntity(
            "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
            "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
            null,
            1622,
            null,
            "en",
            "Supernatural",
            "/o9OKe3M06QMLOzTl3l6GStYtnE9.jpg",
            7.4,
            "Supernatural",
            "2005-09-1"
        )
    }

    @Test
    fun getTvShows() {
        val tvShows = MutableLiveData<List<ItemListEntity>>()
        tvShows.value = FakeData.getDummyMovies()
        Mockito.`when`(data.getMovies()).thenReturn(tvShows)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.tvShows?.observeForever(observer as Observer<List<ItemListEntity>>)
        Mockito.verify(data).getTvShows()
    }

}