package com.example.countries.ui.screens.state


// TODO read difference between out and in keywords
sealed class UIState<out T : Any> {
    object Loading : UIState<Nothing>()
    data class Success<T : Any>(val data: T) : UIState<T>()
    data class Failure(val reason: String?) : UIState<Nothing>()
}