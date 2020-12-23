package com.temel.mvi.usecase.observer.listener

interface ErrorListener : IResponseListener {
    fun failure(throwable: Throwable): Unit
}