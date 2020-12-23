package com.temel.platform.app

import com.temel.mvi.usecase.SingleUseCase
import com.temel.platform.ApiClient
import com.temel.platform.app.entity.Facts
import io.reactivex.Single
import javax.inject.Inject

class GetCatsFactsUseCase @Inject constructor(var apiClient: ApiClient) : SingleUseCase<Facts,  Unit>() {
    override fun invoke(params: Unit): Single<Facts> {
        return apiClient.create<CatsService>("https://cat-fact.herokuapp.com/").facts()
    }
}