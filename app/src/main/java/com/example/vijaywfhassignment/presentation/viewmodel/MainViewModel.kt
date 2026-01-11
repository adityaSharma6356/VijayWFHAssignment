package com.example.vijaywfhassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.vijaywfhassignment.data.remote.dto.TitleDetailsResponse
import com.example.vijaywfhassignment.data.repository.TitlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: TitlesRepository
) : ViewModel() {

    val movies = repo.getPagedMovies().cachedIn(viewModelScope)
    val tvShows = repo.getPagedTvShows().cachedIn(viewModelScope)

    private val _details = MutableStateFlow<Result<TitleDetailsResponse>?>(null)
    val details = _details.asStateFlow()

    fun loadDetails(id: Int) = viewModelScope.launch {
        _details.value = repo.getDetails(id)
    }
}
