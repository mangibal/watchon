package com.iqbalfauzi.watchon.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.databinding.FragmentTvBinding
import com.iqbalfauzi.watchon.ui.MovieAdapter
import com.iqbalfauzi.watchon.ui.detail.DetailActivity
import com.iqbalfauzi.watchon.ui.listener.OnItemClickListener

class TvFragment : Fragment() {

    private lateinit var tvViewModel: TvViewModel
    private lateinit var databinding: FragmentTvBinding
    private lateinit var tvAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tvViewModel = ViewModelProviders.of(this).get(TvViewModel::class.java)
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv, container, false)
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val tvData = tvViewModel.getTvData()
            tvAdapter = MovieAdapter(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    val data = Intent(context, DetailActivity::class.java)
                    data.putExtra(DetailActivity.TYPE, "tv")
                    data.putExtra(DetailActivity.ITEM_ID, tvData[position].id.toString())
                    startActivity(data)
                }
            })
            tvAdapter.setData(tvData)

            with(databinding) {
                rvTv.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvAdapter
                    itemAnimator = DefaultItemAnimator()
                }
            }
        }
    }
}