package com.iqbalfauzi.watchon.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.iqbalfauzi.watchon.databinding.FragmentMovieBinding
import com.iqbalfauzi.watchon.ui.detail.DetailActivity
import com.iqbalfauzi.watchon.ui.listener.OnItemClickListener
import com.iqbalfauzi.watchon.utils.ViewModelFactory
import com.iqbalfauzi.watchon.utils.hide

class MovieFragment : Fragment() {

    private lateinit var dataBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    private var movies = listOf<ResultEntity>()
    private val movieViewModel by lazy {
        val viewModelFactory = activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setMovieAdapter()
        getViewModelData()
    }

    private fun getViewModelData() {
        movieViewModel.getMovies().observe(viewLifecycleOwner, Observer {
            dataBinding.pbLoading.hide()
            movies = it
            movieAdapter.setData(movies)
        })
    }

    private fun setMovieAdapter() {
        with(dataBinding) {
            movieAdapter = MovieAdapter(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    val data = Intent(context, DetailActivity::class.java)
                    data.putExtra(DetailActivity.TYPE, "movie")
                    data.putExtra(DetailActivity.ITEM_ID, movies[position].id.toString())
                    startActivity(data)
                }
            })
            rvMovie.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = movieAdapter
                itemAnimator = DefaultItemAnimator()
            }
        }
    }
}