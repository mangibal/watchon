package com.iqbalfauzi.watchon.ui.movie

import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.utils.DataDummy
import com.iqbalfauzi.watchon.data.ItemEntity

class MovieViewModel : ViewModel() {

    fun getMovies(): List<ItemEntity> {
        return DataDummy.getDataMovies()
    }
}