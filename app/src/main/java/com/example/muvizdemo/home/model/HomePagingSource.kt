package com.example.muvizdemo.home.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class HomePagingSource(
    private val homeApi: HomeApi
) : PagingSource<Int, HomeTrendingTvDayResultResponse>() {

    override fun getRefreshKey(
        state: PagingState<Int, HomeTrendingTvDayResultResponse>
    ): Int? {
        return state.anchorPosition
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, HomeTrendingTvDayResultResponse> {
        return try {
            val nextPage = params.key ?: 1
            val trendingSeriesList = homeApi.getTrendingTvDay(nextPage)
            LoadResult.Page(
                data = trendingSeriesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingSeriesList.results.isEmpty()) null else trendingSeriesList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}