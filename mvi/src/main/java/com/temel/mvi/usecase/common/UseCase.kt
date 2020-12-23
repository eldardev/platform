package com.temel.mvi.usecase.common

import androidx.annotation.Nullable

interface UseCase<out ResponseType, in InputParams> where ResponseType : Any {
    fun invoke(@Nullable params: InputParams):ResponseType
}
