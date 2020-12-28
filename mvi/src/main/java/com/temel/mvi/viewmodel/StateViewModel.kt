package com.temel.mvi.viewmodel

import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.ViewState

abstract class StateViewModel<VS : ViewState> : CommonViewModel() {

    internal val state: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = null
        }

    internal fun selectState(selectedState: VS){
        state.value = selectedState
    }

    internal fun updateState(viewState: VS?) {
        viewState?.let {
            state.postValue(viewState)
        }
    }
}