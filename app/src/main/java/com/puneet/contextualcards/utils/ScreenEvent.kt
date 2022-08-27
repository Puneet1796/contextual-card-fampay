package com.puneet.contextualcards.utils

sealed class ScreenEvent {
    data class UpdateLoadingStateEvent(val isLoading: Boolean) : ScreenEvent()
    data class DataFetched<T>(val value: T) : ScreenEvent()
    data class ErrorEvent(val message: String?) : ScreenEvent()
}
