package com.temel.platform.app.interactor

import com.temel.platform.app.entity.Fact
import com.temel.platform.app.usecase.GetCatsFactsUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainInteractor @Inject constructor(private var getCatsFactsUseCase: GetCatsFactsUseCase) {

    fun getFacts(): Single<List<Fact>> {
        return getCatsFactsUseCase.invoke(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}