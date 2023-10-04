package com.example.countries.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.sources.remote.wrapper.Result
import com.example.countries.domain.model.Countries
import com.example.countries.domain.usecases.GetAllCountriesUseCase
import com.example.countries.ui.screens.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCountries: GetAllCountriesUseCase) :
    ViewModel() {

    // Backing field to avoid state updates from other classes
    private val _homeUIState = MutableStateFlow<UIState<Countries>>(UIState.Loading)
    var homeUIState: StateFlow<UIState<Countries>> = _homeUIState.asStateFlow()
    // Backing property exposed to outside world
    /*var uiState: StateFlow<Countries>
        get() = _uiState
        set(value) {
            _uiState.value = value.value
        }*/

    init {
        getCountries()
    }

    private fun getCountries() = viewModelScope.launch {
        when (val result = getAllCountries()) {
            is Result.Success -> {
                _homeUIState.value = UIState.Success(data = result.data)
            }

            is Result.Error -> {
                _homeUIState.value = UIState.Failure(reason = result.code.toString())
            }

            is Result.Failure -> {
                _homeUIState.value = UIState.Failure(reason = result.exception.message)
            }
        }
    }
}

//Whenever you want to access the uiState within the class you should use the backing field so the _uiState.
//If you want that the age is access outside of the class you will use the backing property _uiState