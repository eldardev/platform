package com.temel.platform.app.usecase

import com.temel.mvi.usecase.SingleUseCase
import com.temel.platform.app.api.ApiClient
import com.temel.platform.app.api.CatsService
import com.temel.platform.app.entity.Fact
import io.reactivex.Single
import javax.inject.Inject

class GetCatsFactsUseCase @Inject constructor(var apiClient: ApiClient) : SingleUseCase<List<Fact>,  Unit> {
    override fun invoke(params: Unit): Single<List<Fact>> {
        return apiClient.create<CatsService>("https://cat-fact.herokuapp.com/").facts()
    }
}