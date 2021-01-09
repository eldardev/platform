package com.temel.mvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.ViewState

abstract class StateViewModel<VS : ViewState> : NavViewModel() {
    internal val state: LiveData<VS>
        get() = mutableState

    internal val mutableState: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = null
        }
}