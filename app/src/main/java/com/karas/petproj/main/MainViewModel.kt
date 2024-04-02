package com.karas.petproj.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karas.petproj.model.Statistics
import com.karas.petproj.network.YouTService
import com.karas.petproj.repository.YouTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
private val youTRepository: YouTRepository
): ViewModel() {

    private val _youTStatistics = MutableStateFlow(Statistics())
    val youTStatistic = _youTStatistics.asStateFlow()

    fun processRequest(videoID: String) {
        viewModelScope.launch {
            val st = youTRepository.getStatistic(videoID)?: Statistics()
            _youTStatistics.value = Statistics(st.etag, st.items, st.kind, st.pageInfo)
        }
    }
}