package com.temel.mvi.usecase

import com.temel.mvi.usecase.common.RxUseCase
import com.temel.mvi.usecase.observer.UseCaseMaybeObserver
import com.temel.mvi.usecase.observer.listener.ErrorListener
import com.temel.mvi.usecase.observer.listener.MaybeResponseListener
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class MaybeUseCase<ResponseType, InputParams> : RxUseCase<Maybe<ResponseType>,
        InputParams,
        UseCaseMaybeObserver<ResponseType>?,
        MaybeResponseListener<ResponseType>>() {

    override fun execute(
        errorListener: ErrorListener,
        params: InputParams,
        listener: MaybeResponseListener<ResponseType>
    ) {
        val maybe: Maybe<ResponseType> = invoke(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        observer = UseCaseMaybeObserver(errorListener, listener)
        checkNotNull(observer)

        addDisposable(maybe.subscribeWith(observer!!))
    }
}
