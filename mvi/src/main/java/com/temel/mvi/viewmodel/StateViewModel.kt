package com.temel.mvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.ViewState

abstract class StateViewModel<VS : ViewState> : DisposeBagViewModel() {

    private val mutableState: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = null
        }

    internal val state: LiveData<VS>
        get() = mutableState

    internal fun updateState(viewState: VS?) {
        viewState?.let {
            mutableState.postValue(viewState)
        }
    }
}