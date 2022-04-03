package com.example.muvizdemo.home.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi
) {
    fun getTrendingThisWeekTvSeries()
            : Flow<PagingData<HomeTrendingTvDayResultResponse>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                HomePagingSource(homeApi)
            }
        ).flow
    }
}