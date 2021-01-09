package com.temel.mvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.temel.mvi.navigation.CoordinatorEvent
import com.temel.mvi.viewstate.ViewState

abstract class NavViewModel: CommonViewModel() {
    internal val event: LiveData<CoordinatorEvent>
        get() = mutableEvent

    internal val mutableEvent: MutableLiveData<CoordinatorEvent> =
        MutableLiveData<CoordinatorEvent>().apply {
            this.value = null
        }
}