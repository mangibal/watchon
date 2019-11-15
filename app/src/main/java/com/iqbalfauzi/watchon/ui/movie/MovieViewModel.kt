package com.iqbalfauzi.watchon.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.iqbalfauzi.watchon.data.repository.DataRepository

class MovieViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getMovies() : LiveData<List<ResultEntity>> = dataRepository.getMovies()

}