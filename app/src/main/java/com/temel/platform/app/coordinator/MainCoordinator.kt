package com.temel.platform.app.coordinator

import com.temel.mvi.navigation.Coordinator
import javax.inject.Inject

class MainCoordinator @Inject constructor(val mainNavigator: MainNavigator): Coordinator {

    fun openListFragment() {

    }

//    fun start(){
//        navigator.showNewsList()
//    }
//
//    fun readNewsArticle(id : Int){
//        navigator.showNewsArticle(id)
//    }
}