package com.temel.mvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.ViewState

abstract class StateViewModel<VS : ViewState> : CommonViewModel() {
    internal val state: LiveData<VS>
        get() = mutableState

    internal val mutableState: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = initState()
        }

    internal val throwable: MutableLiveData<Throwable> =
        MutableLiveData<Throwable>().apply {
            this.value = null
        }

    abstract fun initState(): VS
}