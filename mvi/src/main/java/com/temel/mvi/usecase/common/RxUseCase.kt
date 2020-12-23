package com.temel.mvi.usecase.common

import androidx.annotation.Nullable
import com.temel.mvi.usecase.observer.listener.ErrorListener
import com.temel.mvi.usecase.observer.listener.IResponseListener
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxUseCase<ResponseType, InputParams, Observer, in ResponseListener> :
    UseCase<ResponseType, InputParams>
        where ResponseType : Any, ResponseListener : IResponseListener {
    protected var observer: Observer? = null

    private val disposables = CompositeDisposable()

    abstract fun execute(
        errorListener: ErrorListener,
        @Nullable params: InputParams,
        listener: ResponseListener
    )

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    internal fun dispose() {
        if (!disposables.isDisposed) {
            disposables.clear()
        }
    }
}