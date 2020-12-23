package com.temel.mvi.usecase.observer

import com.temel.mvi.usecase.observer.listener.ErrorListener
import com.temel.mvi.usecase.observer.listener.MaybeResponseListener
import io.reactivex.observers.DisposableMaybeObserver

class UseCaseMaybeObserver<ResponseType>(
    private val errorListener: ErrorListener,
    private val maybeResponseListener: MaybeResponseListener<ResponseType>
) :
    DisposableMaybeObserver<ResponseType>() {
    override fun onSuccess(t: ResponseType) {
        maybeResponseListener.handleSuccess(t)
    }

    override fun onError(throwable: Throwable) {
        errorListener.failure(throwable)
    }

    override fun onComplete() {
        maybeResponseListener.complete()
    }
}