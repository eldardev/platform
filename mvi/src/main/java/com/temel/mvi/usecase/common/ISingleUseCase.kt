package com.temel.mvi.usecase.common

import com.temel.mvi.usecase.observer.listener.ErrorListener

interface ISingleUseCase<InputParams, ResponseType> {
    fun execute(
        errorListener: ErrorListener,
        params: InputParams,
        responseTypeCallback: (responseType: ResponseType) -> Unit
    )
}