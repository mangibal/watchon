package com.iqbalfauzi.watchon.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.di.Injection
import com.iqbalfauzi.watchon.ui.detail.DetailViewModel
import com.iqbalfauzi.watchon.ui.movie.MovieViewModel
import com.iqbalfauzi.watchon.ui.tv.TvViewModel
import java.lang.IllegalArgumentException

/**
 * Created by Iqbal Fauzi on 19:36 02/11/19
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(factoryClass: Class<T>): T {
        return when {
            factoryClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(dataRepository) as T
            factoryClass.isAssignableFrom(TvViewModel::class.java) -> TvViewModel(dataRepository) as T
            factoryClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(dataRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel : ${factoryClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Injection.movieRepository()?.let { ViewModelFactory(it) }
                    }
                }
            }
            return INSTANCE
        }
    }

}