package com.temel.mvi.viewmodel

import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.ViewState

abstract class StateViewModel<VS : ViewState> : CommonViewModel() {

    val state: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = null
        }
}