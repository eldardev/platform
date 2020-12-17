package com.temel.mvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.temel.mvi.viewstate.Intents
import com.temel.mvi.viewstate.ViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

abstract class CommonViewModel<VS : ViewState, I: Intents> : ViewModel() {
    /**
     * Rx
     */
    private val disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    protected fun Disposable.disposeLater(): Disposable {
        disposeBag.add(this)
        return this
    }

    /**
     * ViewState
     */
    private val _state: MutableLiveData<VS> =
        MutableLiveData<VS>().apply {
            this.value = initViewState()
        }

    abstract fun initViewState(): VS

    val state: LiveData<VS>
        get() = _state

    protected val viewState: VS?
        get() = state.value

    protected fun updateState(viewState: VS?){
        viewState?.let {
            _state.postValue(viewState)
        }
    }

    /**
     * Intents
     */

    private val intents = BehaviorSubject.create<I>()

    fun sendIntent(intent: I) {
        intents.onNext(intent)
    }

    init {
        intents.subscribe {
            handleIntents(it)
        }.disposeLater()
    }

    protected abstract fun handleIntents(intent: I)
}