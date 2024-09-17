package com.example.zcg.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zcg.R
import com.zcg.model.Section
import com.zcg.repository.Result
import com.zcg.repository.SectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SectionListUiState(
    val item: List<Section> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sectionRepository: SectionRepository
) : ViewModel() {
    private val _sectionListUiState: MutableStateFlow<SectionListUiState> =
        MutableStateFlow(SectionListUiState())
    val sectionListUiState: StateFlow<SectionListUiState> get() = _sectionListUiState

    init {
        viewModelScope.launch {
            getSections()
        }
    }

    private suspend fun getSections() {
        _sectionListUiState.update {
            it.copy(isLoading = true)
        }
        val result = sectionRepository.getSections()
        _sectionListUiState.update {
            it.copy(isLoading = false)
        }
        when(result) {
            is Result.Error -> {
                _sectionListUiState.update {
                    it.copy(userMessage = R.string.error)
                }
            }
            is Result.Success -> {
                _sectionListUiState.update {
                    it.copy(item = result.data)
                }
            }
        }
    }
}
