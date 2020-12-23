package com.temel.mvi.usecase

import com.temel.mvi.usecase.common.RxUseCase
import com.temel.mvi.usecase.observer.UseCaseCompletableObserver
import com.temel.mvi.usecase.observer.listener.CompletableResponseListener
import com.temel.mvi.usecase.observer.listener.ErrorListener
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<InputParams> : RxUseCase<Completable,
        InputParams,
        UseCaseCompletableObserver?,
        CompletableResponseListener>() {

    override fun execute(
        errorListener: ErrorListener, params: InputParams,
        listener: CompletableResponseListener
    ) {
        observer = UseCaseCompletableObserver(errorListener) {
            listener.complete()
        }

        val completable: Completable = invoke(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        checkNotNull(observer)
        addDisposable(completable.subscribeWith(observer!!))
    }
}
