package com.example.muvizdemo.home.model

import com.google.gson.annotations.SerializedName

data class HomeTrendingTvDayResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<HomeTrendingTvDayResultResponse>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)