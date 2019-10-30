package com.iqbalfauzi.watchon.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.databinding.FragmentMovieBinding
import com.iqbalfauzi.watchon.ui.MovieAdapter
import com.iqbalfauzi.watchon.ui.detail.DetailActivity
import com.iqbalfauzi.watchon.ui.listener.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var dataBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movies = movieViewModel.getMovies()
            movieAdapter = MovieAdapter(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    val data = Intent(context, DetailActivity::class.java)
                    data.putExtra(DetailActivity.TYPE, "movie")
                    data.putExtra(DetailActivity.ITEM_ID, movies[position].id.toString())
                    startActivity(data)
                }
            })
            movieAdapter.setData(movies)

            dataBinding.rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
                itemAnimator = DefaultItemAnimator()
            }
        }
    }
}