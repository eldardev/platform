package com.temel.mvi.usecase.observer.listener

interface MaybeResponseListener<T> :
    IResponseListener {
    fun handleSuccess(t: T): Unit
    fun complete()
}