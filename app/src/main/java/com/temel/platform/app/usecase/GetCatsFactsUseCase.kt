package com.temel.platform.app.usecase

import com.temel.mvi.usecase.SingleUseCase
import com.temel.platform.app.api.ApiServiceProvider
import com.temel.platform.app.api.CatsService
import com.temel.platform.app.entity.Fact
import io.reactivex.Single
import javax.inject.Inject

class GetCatsFactsUseCase @Inject constructor(var apiServiceProvider: ApiServiceProvider) : SingleUseCase<List<Fact>,  Unit> {
    override fun invoke(params: Unit): Single<List<Fact>> {
        return apiServiceProvider.get<CatsService>("https://cat-fact.herokuapp.com/").facts()
    }
}