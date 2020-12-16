package com.temel.platform.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.temel.platform.app.MainIntent
import com.temel.platform.viewstate.ViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

abstract class CommonViewModel<VS : ViewState> : ViewModel() {
    private val disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    protected fun Disposable.disposeLater(): Disposable {
        disposeBag.add(this)
        return this
    }

    private val _state: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = null
        }

    val state: LiveData<VS>
        get() = _state

    private val intents = BehaviorSubject.create<MainIntent>()

    fun sendIntent(intent: MainIntent) {
        intents.onNext(intent)
    }

    init {
        intents.subscribe {
            handleIntents(it)
        }
    }

    protected fun updateState(viewState: VS){
        _state.postValue(viewState)
    }

    abstract fun handleIntents(mainIntent: MainIntent)
}