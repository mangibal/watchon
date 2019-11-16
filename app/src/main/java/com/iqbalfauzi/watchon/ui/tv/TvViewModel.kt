package com.iqbalfauzi.watchon.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.utils.DataDummy
import com.iqbalfauzi.watchon.data.model.ItemEntity
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.model.ItemListEntity
import com.iqbalfauzi.watchon.data.model.ResultEntity

class TvViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<ResultEntity>> = dataRepository.getTvShows()

}