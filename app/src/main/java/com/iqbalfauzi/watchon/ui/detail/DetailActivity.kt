package com.iqbalfauzi.watchon.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iqbalfauzi.watchon.BuildConfig
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.data.model.ItemListEntity
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.iqbalfauzi.watchon.databinding.ActivityDetailBinding
import com.iqbalfauzi.watchon.utils.Utils
import com.iqbalfauzi.watchon.utils.ViewModelFactory
import com.iqbalfauzi.watchon.utils.hide

class DetailActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityDetailBinding
    private var type = ""
    private var itemId = ""
    private val viewModel by lazy {
        val viewModelFactory = ViewModelFactory.getInstance()
        ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        getIntentExtra()
        setToolbar()
        setContent()
    }

    private fun setContent() {
        viewModel.itemId = itemId
        if (type == "movie") {
            viewModel.getMovieDetail(itemId).observe(this, Observer {
                showData(it)
            })
        } else {
            viewModel.getTvDetail(itemId).observe(this, Observer {
                showData(it)
            })
        }
    }

    private fun showData(data: ResultEntity) {
        val url = BuildConfig.BASE_URL_IMAGE
        with(dataBinding) {
            pbLoading.hide()
            tvTitleDetail.text = data.title?:data.name
            tvDateDetail.text = if (type == "movie") {
                data.release_date ?: "-"
            } else data.first_air_date ?: "-"
            tvScore.text = data.vote_average.toString()
            tvOverviewDetail.text = data.overview
            Glide.with(root)
                    .load(url + data.backdrop_path)
                    .apply(RequestOptions().centerCrop())
                    .apply(RequestOptions().placeholder(Utils.createCircularProgressDrawable(this@DetailActivity)))
                    .apply(RequestOptions().error(R.drawable.ic_broken_image))
                    .into(ivPoster)
        }
    }

    private fun getIntentExtra() {
        type = intent.getStringExtra(TYPE)!!
        itemId = intent.getStringExtra(ITEM_ID)!!
    }

    private fun setToolbar() {
        with(dataBinding) {
            setSupportActionBar(toolbar)
            toolbar.apply {
                title = ""
                navigationIcon = ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_arrow_back_white)
                setNavigationOnClickListener { onBackPressed() }
            }
        }
    }

    companion object {
        const val ITEM_ID = "item_id"
        const val TYPE = "type"
    }

}
