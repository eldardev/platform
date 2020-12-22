package com.temel.mvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.ViewState

abstract class StateViewModel<VS : ViewState> : CommonViewModel() {

    private val _state: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = initViewState()
        }

    abstract fun initViewState(): VS

    val state: LiveData<VS>
        get() = _state

    internal val viewState: VS?
        get() = state.value

    internal fun updateState(viewState: VS?) {
        viewState?.let {
            _state.postValue(viewState)
        }
    }
}