package com.simple


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavHostController

import com.kiwi.navigationcompose.typed.composable
import com.kiwi.navigationcompose.typed.createRoutePattern
import kotlinx.serialization.ExperimentalSerializationApi
import androidx.navigation.compose.NavHost
import com.simple.navigation.NavigationConfig

import com.simple.views.LeagueTrackListView
import com.simple.views.MainScreen

import kotlinx.coroutines.ExperimentalCoroutinesApi



@OptIn(ExperimentalSerializationApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun NavGraph(
    onThemeToggle: (offset: Offset) -> Unit,
    navController: NavHostController
) {


    NavHost(
        navController = navController,
        startDestination = createRoutePattern<NavigationConfig.Main>(),
        enterTransition = { fadeIn(tween(700), 1f) },
        exitTransition = { fadeOut(tween(700), 1f) },
        popEnterTransition = { fadeIn(tween(700), 1f) },
        popExitTransition = { fadeOut(tween(700), 1f) },
    )
    {
        composable<NavigationConfig.Main> {MainScreen(navController, onThemeToggle) }
        composable<NavigationConfig.SettingView> { LeagueTrackListView(navController) }
    }


}


