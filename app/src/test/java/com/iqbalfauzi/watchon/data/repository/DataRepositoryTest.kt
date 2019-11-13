package com.iqbalfauzi.watchon.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iqbalfauzi.watchon.FakeData
import com.iqbalfauzi.watchon.LiveDataTest
import com.iqbalfauzi.watchon.data.repository.local.LocalRepository
import com.iqbalfauzi.watchon.data.repository.remote.RemoteRepositoryJava
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Iqbal Fauzi on 13:53 10/11/19
 */
class DataRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localRepository = mock(LocalRepository::class.java)
    private val remoteRepository = mock(RemoteRepositoryJava::class.java)
    private val dataRepositoryTest = FakeDataRepository(localRepository, remoteRepository)

    private val movieList = FakeData.getDummyMovies()
    private val movieId = movieList[0].id.toString()
    private lateinit var movie : ItemListEntity

    private val tvShowList = FakeData.getDummyTvShows()
    private val tvShowId = tvShowList[0].id.toString()
    private lateinit var tvShow : ItemListEntity

    @Before
    fun setUp() {
        movie = ItemListEntity(
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

        tvShow = ItemListEntity(
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

    @After
    fun tearDown() {

    }

    @Test
    fun getMovies() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepositoryJava.GetMovieCallback
            callback.onSuccess(movieList)
            null
        }.`when`(remoteRepository).getMovies(any(RemoteRepositoryJava.GetMovieCallback::class.java))

        val result = LiveDataTest.getValue(dataRepositoryTest.getMovies())
        verify(remoteRepository, times(1)).getMovies(any(RemoteRepositoryJava.GetMovieCallback::class.java))

        assertEquals(movieList.size, result.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepositoryJava.GetMovieDetailCallback
            callback.onSuccess(movieList[0])
            null
        }.`when`(remoteRepository).getMovieDetail(eq(movieId), any(RemoteRepositoryJava.GetMovieDetailCallback::class.java))
    }

    @Test
    fun getTvShows() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepositoryJava.GetTvShowsCallback
            callback.onSuccess(tvShowList)
        }.`when`(remoteRepository).getTvShows(any(RemoteRepositoryJava.GetTvShowsCallback::class.java))

        val result = LiveDataTest.getValue(dataRepositoryTest.getTvShows())
        verify(remoteRepository, times(1)).getTvShows(any(RemoteRepositoryJava.GetTvShowsCallback::class.java))

        assertEquals(movieList.size, result.size)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepositoryJava.GetTvShowDetailCallback
            callback.onSuccess(tvShowList[0])
            null
        }.`when`(remoteRepository).getTvShowDetail(eq(tvShowId), any(RemoteRepositoryJava.GetTvShowDetailCallback::class.java))
    }
}