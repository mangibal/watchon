package com.iqbalfauzi.watchon.data.model

/**
 * Created by Iqbal Fauzi on 15:49 14/11/19
 */
data class DataResponse(
    val dates: Dates,
    val page: Int,
    val results: List<ResultEntity>,
    val total_pages: Int,
    val total_results: Int
)