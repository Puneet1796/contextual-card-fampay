package com.puneet.contextualcards.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puneet.contextualcards.network.Repository
import com.puneet.contextualcards.utils.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _events = Channel<ScreenEvent>()
    val events: Flow<ScreenEvent>
        get() = _events.receiveAsFlow()

    fun getData() {
        viewModelScope.launch {
            _events.send(ScreenEvent.UpdateLoadingStateEvent(true))
            val data = repository.getData()
            _events.send(ScreenEvent.UpdateLoadingStateEvent(false))
            if (data.isSuccess && data.getOrNull() != null) {
                _events.send(ScreenEvent.DataFetched(data.getOrNull()!!))
            } else {
                _events.send(ScreenEvent.ErrorEvent(data.exceptionOrNull()?.message))
            }
        }
    }
}
