package com.temel.mvi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class CommonViewModel : ViewModel() {

    private val disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    protected fun Disposable.disposeLater(): Disposable {
        disposeBag.add(this)
        return this
    }

    internal val throwable = MutableLiveData<Throwable>().apply {
        this.value = null
    }

    fun setThrowable(throwable: Throwable){
        this.throwable.postValue(throwable)
    }
}