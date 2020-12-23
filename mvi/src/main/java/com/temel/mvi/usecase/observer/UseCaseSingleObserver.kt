package com.temel.mvi.usecase.observer

import com.temel.mvi.usecase.observer.listener.ErrorListener
import io.reactivex.observers.DisposableSingleObserver

class UseCaseSingleObserver<ResponseType>(
    private val errorListener: ErrorListener,
    private val responseTypeCallback: (response: ResponseType) -> Unit
) :
    DisposableSingleObserver<ResponseType>() {
    override fun onSuccess(t: ResponseType) {
        responseTypeCallback(t)
    }

    override fun onError(throwable: Throwable) {
        errorListener.failure(throwable)
    }
}