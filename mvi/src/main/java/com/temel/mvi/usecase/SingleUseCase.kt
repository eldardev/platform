package com.temel.mvi.usecase

import com.temel.mvi.usecase.common.RxUseCase
import com.temel.mvi.usecase.common.ISingleUseCase
import com.temel.mvi.usecase.observer.UseCaseSingleObserver
import com.temel.mvi.usecase.observer.listener.ErrorListener
import com.temel.mvi.usecase.observer.listener.SingleResponseListener
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<ResponseType, InputParams> :
    RxUseCase<Single<ResponseType>,
            InputParams,
            UseCaseSingleObserver<ResponseType>,
            SingleResponseListener<ResponseType>>(),
    ISingleUseCase<InputParams, ResponseType> {

    override fun execute(
        errorListener: ErrorListener,
        params: InputParams,
        listener: SingleResponseListener<ResponseType>
    ) {
        val single: Single<ResponseType> = invoke(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        observer = UseCaseSingleObserver(errorListener) {
            listener.handleSuccess(it)
        }

        checkNotNull(observer)

        addDisposable(single.subscribeWith(observer!!))
    }

    override fun execute(
        errorListener: ErrorListener,
        params: InputParams,
        responseTypeCallback: (responseType: ResponseType) -> Unit
    ) {
        val single: Single<ResponseType> = invoke(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        observer = UseCaseSingleObserver(errorListener) {
            responseTypeCallback(it)
        }

        checkNotNull(observer)

        addDisposable(single.subscribeWith(observer!!))
    }
}
