package com.example.vijaywfhassignment.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vijaywfhassignment.data.remote.dto.TitleDetailsResponse
import com.example.vijaywfhassignment.data.repository.TitlesRepository
import com.example.vijaywfhassignment.util.Resource
import com.example.vijaywfhassignment.util.toReadableMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TitleDetailsViewModel @Inject constructor(
    private val repo: TitlesRepository
) : ViewModel() {
    private val _details = MutableStateFlow<Resource<TitleDetailsResponse>>(Resource.Loading)
    val details = _details.asStateFlow()

    fun loadDetails(id: Int) = viewModelScope.launch {
        val result = repo.getDetails(id)

        result.onSuccess { data ->
            _details.value = Resource.Success(data)
        }

        result.onFailure { throwable ->
            _details.value = Resource.Error(throwable.toReadableMessage())
        }
    }
}
