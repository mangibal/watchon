package com.iqbalfauzi.watchon.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Iqbal Fauzi on 18:32 02/11/19
 */
data class ItemResponse(
        @SerializedName("results")
        val results: List<ItemListEntity>?)