package com.example.ybsproject

sealed class ViewState<out S, out F> {
    data object Loading : ViewState<Nothing, Nothing>()
    data class Success<out S>(val result: S) : ViewState<S, Nothing>()
    data class Error<out F>(val error: F) : ViewState<Nothing, F>()
}
