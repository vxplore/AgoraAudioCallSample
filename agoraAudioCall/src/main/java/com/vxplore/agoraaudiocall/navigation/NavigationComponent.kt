package com.hellomydoc.videocall.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.vxplore.agoraaudiocall.AudioRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationComponent(
    navHostController: NavHostController,
    /*paddingValues: PaddingValues,*/
    data: Any? = null
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = AudioRoute.route,
        modifier = Modifier/*.padding(paddingValues)*/
    ) {
        AudioRoute.composable(this, navHostController, data)
        /*ChatRoute.composable(
            this, navHostController
        )*/
    }
}