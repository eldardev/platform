package com.temel.mvi.usecase

import androidx.annotation.Nullable
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface UseCase<out ResponseType, in InputParams> where ResponseType : Any {
    fun invoke(@Nullable params: InputParams):ResponseType
}

interface SingleUseCase<ResponseType, InputParams> : UseCase<Single<ResponseType>, InputParams>

interface MaybeUseCase<ResponseType, InputParams> : UseCase<Maybe<ResponseType>, InputParams>

interface CompletableUseCase<InputParams> : UseCase<Completable, InputParams>
