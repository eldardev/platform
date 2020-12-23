package com.temel.mvi.usecase.observer.listener

interface SingleResponseListener<T> : IResponseListener {
    fun handleSuccess(t: T): Unit
}