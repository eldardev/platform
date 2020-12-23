package com.temel.platform.app

import com.temel.platform.app.entity.Facts
import io.reactivex.Single
import retrofit2.http.*

interface CatsService {

    @GET("facts")
    fun facts(): Single<Facts>
}