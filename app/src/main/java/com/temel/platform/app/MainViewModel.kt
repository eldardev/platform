package com.temel.platform.app

import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.platform.app.state.MainState
import com.temel.platform.app.usecase.GetCatsFactsUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var getCatsFactsUseCase: GetCatsFactsUseCase
) :
    StoreViewModel<MainViewModel.MainAction,
            MainViewModel.MainCommand,
            MainState>() {

    sealed class MainAction : Action {
        class ChangeText(val text: String) : MainAction()
        class SetLoading(val isLoading: Boolean) : MainAction()
    }

    sealed class MainCommand : Command {
        object FetchFacts : MainCommand()
    }

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
                setThrowable(it)
            }
            .map<MainAction> {
                MainAction.ChangeText(it.toString())
            }
            .toObservable()
            .startWith(MainAction.SetLoading(true))
    }
}