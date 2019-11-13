package com.iqbalfauzi.watchon.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.iqbalfauzi.watchon.BuildConfig
import com.iqbalfauzi.watchon.data.repository.ItemListEntity
import com.iqbalfauzi.watchon.databinding.ItemRvMovieBinding
import com.iqbalfauzi.watchon.ui.listener.OnItemClickListener

/**
 * Created by Iqbal Fauzi on 16:59 16/10/19
 */
class MovieAdapter(val listener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.Item>() {

    private var list: List<ItemListEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvMovieBinding.inflate(inflater)
        return Item(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(movies: List<ItemListEntity>) {
        list = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Item, position: Int) = holder.bind(list[position])

    inner class Item(private val binding: ItemRvMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(it, adapterPosition)
            }
        }

        fun bind(item: ItemListEntity) {
            val url = BuildConfig.BASE_URL_IMAGE
            with(binding) {
                tvTitle.text = item.originalTitle
                tvDate.text = item.releaseDate
                tvOverview.text = item.overview
                Glide.with(binding.root)
                        .load(url + item.posterPath)
                        .apply(RequestOptions().centerCrop())
                        .apply(RequestOptions().transform(RoundedCorners(54)))
                        .into(ivPoster)
            }
        }

    }

}