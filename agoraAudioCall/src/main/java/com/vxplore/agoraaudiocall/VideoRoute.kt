package com.vxplore.agoraaudiocall

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hellomydoc.videocall.navigation.NavRoute
import com.hellomydoc.videocall.navigation.Routes

object VideoRoute : NavRoute<AudioCalViewModel> {

    override val route = Routes.Video

    @Composable
    override fun viewModel(): AudioCalViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: AudioCalViewModel, data: Any?) = VideoPage(viewModel.apply {
        setData(data)
    })

    override fun getArguments(): List<NamedNavArgument> {
        return listOf(
            navArgument("channelId") { type = NavType.StringType },
            navArgument("userId") { type = NavType.StringType },
            navArgument("peerId") { type = NavType.StringType }
        )
    }
}

@Composable
private fun VideoPage(apply: AudioCalViewModel) {

}