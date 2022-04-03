package com.example.muvizdemo.home.model

import com.example.muvizdemo.BuildConfig.API_KEY
import com.example.muvizdemo.common.model.MDConstants.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("trending/tv/day")
    suspend fun getTrendingTvDay(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): HomeTrendingTvDayResponse
}