package com.iqbalfauzi.watchon.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.data.ItemEntity
import com.iqbalfauzi.watchon.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: ActivityDetailBinding
    private var data = ItemEntity()
    private var type = ""
    private var itemId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        getIntentExtra()
        setToolbar()
        setDetailData()
    }

    private fun setDetailData() {
        viewModel.itemId = itemId
        data = if (type == "movie") {
            viewModel.getMovieDetail()
        } else {
            viewModel.getTvDetail()
        }
        with(dataBinding) {
            tvTitle.text = data.title
            tvDate.text = data.date
            tvScore.text = data.score.toString()
            tvOverview.text = data.overview
            Glide.with(root)
                    .load(data.poster)
                    .apply(RequestOptions().centerCrop())
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
            supportActionBar!!.title = ""
            toolbar.apply {
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
