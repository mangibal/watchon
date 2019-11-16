package com.iqbalfauzi.watchon.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.iqbalfauzi.watchon.BuildConfig
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.iqbalfauzi.watchon.databinding.ItemRvMovieBinding
import com.iqbalfauzi.watchon.ui.listener.OnItemClickListener

/**
 * Created by Iqbal Fauzi on 16:59 16/10/19
 */
class TvAdapter(val listener: OnItemClickListener) : RecyclerView.Adapter<TvAdapter.Item>() {

    private var list : List<ResultEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvMovieBinding.inflate(inflater)
        return Item(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(tvShows: List<ResultEntity>) {
        list = tvShows
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Item, position: Int) = holder.bind(list[position])

    inner class Item(private val binding: ItemRvMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(it, adapterPosition)
            }
        }

        fun bind(item: ResultEntity) {
            with(binding) {
                tvTitle.text = item.original_name
                tvDate.text = item.first_air_date
                tvOverview.text = item.overview
                Glide.with(binding.root)
                    .load("${BuildConfig.BASE_URL_IMAGE}${item.poster_path}")
                    .apply(RequestOptions().centerCrop())
                    .apply(RequestOptions().transform(RoundedCorners(54)))
                    .apply(RequestOptions().error(R.drawable.ic_broken_image))
                    .into(ivPoster)
            }
        }

    }

}