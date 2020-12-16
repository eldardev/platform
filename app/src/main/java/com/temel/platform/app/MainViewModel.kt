package com.temel.platform.app

import com.temel.platform.viewmodel.CommonViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : CommonViewModel<MainState>() {

    fun test(){
        println("Test")
    }

    override fun handleIntents(mainIntent: MainIntent) {
        when(mainIntent){
            is MainIntent.ChangeText -> updateState(MainState(mainIntent.textIntent))
        }
    }
}