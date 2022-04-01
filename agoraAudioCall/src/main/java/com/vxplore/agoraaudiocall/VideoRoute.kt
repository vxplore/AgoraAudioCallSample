package com.vxplore.agoraaudiocall

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import com.hellomydoc.videocall.navigation.NavRoute
import com.hellomydoc.videocall.navigation.Routes

object VideoRoute : NavRoute<VideoViewModel> {

    override val route = Routes.Video

    @Composable
    override fun viewModel(): VideoViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: VideoViewModel, data: Any?) = VideoPage(viewModel.apply {
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