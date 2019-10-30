package com.iqbalfauzi.watchon.ui.detail

import androidx.lifecycle.ViewModel
import com.iqbalfauzi.watchon.utils.DataDummy
import com.iqbalfauzi.watchon.data.ItemEntity

/**
 * Created by Iqbal Fauzi on 10:49 24/10/19
 */
class DetailViewModel : ViewModel() {

    val dataMovies = DataDummy.getDataMovies()
    val dataTv = DataDummy.getDataTv()
    private var item = ItemEntity()
    var itemId = ""

    fun getMovieDetail(): ItemEntity {
        for (i in dataMovies.indices) {
            val movie = DataDummy.getDataMovies()[i]
            if (movie.id.toString() == itemId) {
                item = movie
            }
        }
        return item
    }

    fun getTvDetail(): ItemEntity {
        for (i in dataTv.indices) {
            val tv = DataDummy.getDataTv()[i]
            if (tv.id.toString() == itemId) {
                item = tv
            }
        }
        return item
    }

}