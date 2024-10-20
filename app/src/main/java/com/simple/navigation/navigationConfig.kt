package com.simple.navigation

import com.kiwi.navigationcompose.typed.Destination
import com.kiwi.navigationcompose.typed.ResultDestination
import kotlinx.serialization.Serializable

internal sealed interface NavigationConfig : Destination {

    // Main Route
    @Serializable
    data object Main : NavigationConfig

    // Route with parameter
    @Serializable
    data class EventRout(val eventID: Int = 0) : NavigationConfig

    //FOR DIALOG ROUTE and Result from Destination
    @Serializable
    data object DialogRoute : NavigationConfig , ResultDestination<String> {
        @Serializable
        data class Result(val time: String)
    }


    // Setting View
    @Serializable
    data object SettingView : NavigationConfig
}




