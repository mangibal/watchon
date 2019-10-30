package com.iqbalfauzi.watchon.ui.tv

import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.utils.DataDummy
import com.iqbalfauzi.watchon.data.ItemEntity

class TvViewModel : ViewModel() {

    fun getTvData(): List<ItemEntity> {
        return DataDummy.getDataTv()
    }
}