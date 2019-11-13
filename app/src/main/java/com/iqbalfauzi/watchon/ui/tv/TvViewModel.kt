package com.iqbalfauzi.watchon.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.utils.DataDummy
import com.iqbalfauzi.watchon.data.ItemEntity
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.repository.ItemListEntity

class TvViewModel(dataRepository: DataRepository) : ViewModel() {

    val tvShows : LiveData<List<ItemListEntity>> = dataRepository.getTvShows()

    fun getTvData(): List<ItemEntity> {
        return DataDummy.getDataTv()
    }
}