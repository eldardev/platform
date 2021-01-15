package com.temel.platform.app.usecase

import com.temel.mvi.usecase.UseCase
import com.temel.platform.app.api.ApiServiceProvider
import com.temel.platform.app.api.CatsService
import com.temel.platform.app.feature.main.MainAction
import com.temel.platform.app.feature.main.MainState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetCatsFactsUseCase @Inject constructor(
    private var apiServiceProvider: ApiServiceProvider
) : UseCase<MainAction, MainState> {
    override fun invoke(
        action: Observable<MainAction>,
        state: MainState
    ): Observable<MainAction> =
        action.ofType(MainAction.FetchFacts::class.java)
            .switchMap {
                apiServiceProvider.get<CatsService>("https://cat-fact.herokuapp.com/")
                    .facts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map<MainAction> {
                        MainAction.ChangeText(it.toString())
                    }
                    .toObservable()
                    .startWith(MainAction.Loading)
            }
}