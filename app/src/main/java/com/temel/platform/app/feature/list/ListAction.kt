package com.temel.platform.app.feature.list

import com.temel.mvi.viewstate.Action

sealed class ListAction : Action{
    object LoadList : ListAction()
}