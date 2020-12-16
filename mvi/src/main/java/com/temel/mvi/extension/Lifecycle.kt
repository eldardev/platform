package com.temel.mvi.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.temel.mvi.viewstate.ViewState

fun <L : LiveData<Throwable>> LifecycleOwner.throwable(liveData: L, body: (Throwable?) -> Unit) =
        liveData.observe(this, Observer(body))


fun <T : ViewState, L : LiveData<T>> LifecycleOwner.renderViewState(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))