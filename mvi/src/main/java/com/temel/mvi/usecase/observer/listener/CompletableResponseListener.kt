package com.temel.mvi.usecase.observer.listener

interface CompletableResponseListener :
    IResponseListener {
    fun complete()
}