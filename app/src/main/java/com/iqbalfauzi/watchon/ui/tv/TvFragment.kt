package com.iqbalfauzi.watchon.ui.tv

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
import com.iqbalfauzi.watchon.data.repository.ItemListEntity
import com.iqbalfauzi.watchon.databinding.FragmentTvBinding
import com.iqbalfauzi.watchon.ui.TvAdapter
import com.iqbalfauzi.watchon.ui.detail.DetailActivity
import com.iqbalfauzi.watchon.ui.listener.OnItemClickListener
import com.iqbalfauzi.watchon.utils.ViewModelFactory
import com.iqbalfauzi.watchon.utils.hide

class TvFragment : Fragment() {

    private lateinit var databinding: FragmentTvBinding
    private lateinit var tvAdapter: TvAdapter
    private var tvShows = listOf<ItemListEntity>()

    private val tvViewModel by lazy {
        val viewModelFactory = activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this, viewModelFactory).get(TvViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv, container, false)
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            with(databinding) {
                tvAdapter = TvAdapter(object : OnItemClickListener {
                    override fun onItemClick(itemView: View, position: Int) {
                        val data = Intent(context, DetailActivity::class.java)
                        data.putExtra(DetailActivity.TYPE, "tv")
                        data.putExtra(DetailActivity.ITEM_ID, tvShows[position].id.toString())
                        startActivity(data)
                    }
                })
                tvViewModel.tvShows.observe(viewLifecycleOwner, Observer {
                    pbLoading.hide()
                    tvShows = it
                    tvAdapter.setData(tvShows)
                })
                rvTvShow.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvAdapter
                    itemAnimator = DefaultItemAnimator()
                }
            }
        }
    }
}