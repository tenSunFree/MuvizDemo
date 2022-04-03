package com.example.muvizdemo.home.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.muvizdemo.home.model.HomeRepository
import com.example.muvizdemo.home.model.HomeTrendingTvDayResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
) : ViewModel() {

    private val _resultResponseState =
        mutableStateOf<Flow<PagingData<HomeTrendingTvDayResultResponse>>>(emptyFlow())
    val resultResponseState: State<Flow<PagingData<HomeTrendingTvDayResultResponse>>> =
        _resultResponseState

    init {
        getTrendingThisWeekTvSeries(null)
    }

    private fun getTrendingThisWeekTvSeries(genreId: Int? = null) {
        viewModelScope.launch {
            _resultResponseState.value = if (genreId != null) {
                repository.getTrendingThisWeekTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                repository.getTrendingThisWeekTvSeries().cachedIn(viewModelScope)
            }
        }
    }
}