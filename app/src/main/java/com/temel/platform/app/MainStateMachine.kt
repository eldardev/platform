package com.temel.platform.app

import com.temel.mvi.viewmodel.StateMachine
import com.temel.platform.app.state.MainState
import com.temel.platform.app.usecase.GetCatsFactsUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainStateMachine @Inject constructor(private val getCatsFactsUseCase: GetCatsFactsUseCase) :
    StateMachine<MainAction, MainCommand, MainState>() {

    override fun reduce(action: MainAction, state: MainState): MainState {
        return when (action) {
            is MainAction.ChangeText -> {
                state.apply {
                    text = action.text
                    isLoading = false
                }
            }
            is MainAction.SetLoading -> {
                state.apply {
                    text = ""
                    isLoading = action.isLoading
                }
            }
        }
    }

    override fun call(command: MainCommand): (MainState) -> Observable<MainAction> {
        return when (command) {
            is MainCommand.FetchFacts -> ::getFacts
        }
    }

    private fun getFacts(state: MainState): Observable<MainAction> {
        return getCatsFactsUseCase.invoke(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .map<MainAction> {
                MainAction.ChangeText(it.toString())
            }
            .toObservable()
            .startWith(MainAction.SetLoading(true))
    }
}