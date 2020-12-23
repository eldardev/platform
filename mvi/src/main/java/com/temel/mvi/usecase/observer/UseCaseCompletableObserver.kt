package com.temel.mvi.usecase.observer

import com.temel.mvi.usecase.observer.listener.ErrorListener
import io.reactivex.observers.DisposableCompletableObserver

class UseCaseCompletableObserver(
    private val errorListener: ErrorListener,
    private val complete:()->Unit
) :
    DisposableCompletableObserver() {

    override fun onError(throwable: Throwable) {
        errorListener.failure(throwable)
    }

    override fun onComplete() {
        complete()
    }

}