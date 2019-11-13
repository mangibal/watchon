package com.iqbalfauzi.watchon.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.utils.DataDummy
import com.iqbalfauzi.watchon.data.ItemEntity
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.repository.ItemListEntity

class MovieViewModel(dataRepository: DataRepository) : ViewModel() {

    val movies : LiveData<List<ItemListEntity>> = dataRepository.getMovies()

    fun getMovies(): List<ItemEntity> {
        return DataDummy.getDataMovies()
    }
}