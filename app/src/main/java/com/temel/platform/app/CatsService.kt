package com.temel.platform.app

import com.temel.platform.app.entity.Fact
import io.reactivex.Single
import retrofit2.http.*

interface CatsService {

    @GET("facts")
    fun facts(): Single<List<Fact>>
}