package com.iqbalfauzi.watchon.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.data.ItemEntity
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.repository.ItemListEntity
import com.iqbalfauzi.watchon.utils.DataDummy

/**
 * Created by Iqbal Fauzi on 10:49 24/10/19
 */
class DetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val dataMovies = DataDummy.getDataMovies()
    val dataTv = DataDummy.getDataTv()
    private var item = ItemEntity()
    var itemId = ""

    fun getMovieDetail(movieId: String): LiveData<ItemListEntity> = dataRepository.getMovieDetail(movieId)

    fun getTvDetail(tvId: String): LiveData<ItemListEntity> = dataRepository.getTvShowDetail(tvId)

}