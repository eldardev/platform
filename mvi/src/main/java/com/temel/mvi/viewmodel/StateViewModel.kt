package com.temel.mvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.ViewState

open class StateViewModel<VS : ViewState> : CommonViewModel() {
    internal val state: LiveData<VS>
        get() = mutableState

    //ВРЕМЕННО
    val mutableState: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = null
        }
}