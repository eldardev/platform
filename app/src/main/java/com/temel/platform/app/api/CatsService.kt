package com.temel.platform.app.api

import com.temel.platform.app.entity.Fact
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface CatsService {

    @GET("facts")
    fun facts(): Single<List<Fact>>

    @GET("facts")
    fun facts2(): Completable
}