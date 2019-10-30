package com.iqbalfauzi.watchon.data

/**
 * Created by Iqbal Fauzi on 15:33 16/10/19
 */
data class ItemEntity(
    var id: Int?,
    var title: String?,
    var date: String?,
    var score: Int?,
    var overview: String?,
    var poster: Int?
) {
    constructor() : this(null, null, null, null, null, null)
}