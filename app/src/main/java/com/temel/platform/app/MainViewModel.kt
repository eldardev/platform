package com.temel.platform.app

import com.temel.platform.viewmodel.CommonViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : CommonViewModel<MainState, MainIntent>() {

    fun test(){
        println("Test")
    }

    override fun handleIntents(intent: MainIntent) {
        when(intent){
            is MainIntent.ChangeText -> updateState(MainState(intent.textIntent))
        }
    }
}