package com.example.countries.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.sources.remote.wrapper.Result
import com.example.countries.domain.model.Country
import com.example.countries.domain.usecases.GetCountryByNameUseCase
import com.example.countries.ui.screens.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getCountryByName: GetCountryByNameUseCase) :
    ViewModel() {

    private val _detailUIState = MutableStateFlow<UIState<Country>>(UIState.Loading)
    var uiState = _detailUIState.asStateFlow()

    fun loadCountryData(countryName: String) = viewModelScope.launch {
        when (val result = getCountryByName(countryName = countryName)) {
            is Result.Success -> {
                _detailUIState.value = UIState.Success(data = result.data)
            }

            is Result.Error -> {
                _detailUIState.value = UIState.Failure(reason = result.code.toString())
            }

            is Result.Failure -> {
                _detailUIState.value = UIState.Failure(reason = result.exception.message)
            }
        }
    }
}