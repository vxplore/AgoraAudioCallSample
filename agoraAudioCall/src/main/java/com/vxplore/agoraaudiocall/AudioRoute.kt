package com.vxplore.agoraaudiocall

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hellomydoc.videocall.navigation.NavRoute
import com.vxplore.agoraaudiocall.composables.AudioPage
import com.vxplore.agoraaudiocall.composables.UIRequirePermissions
import com.vxplore.agoraaudiocall.navigation.Routes

object AudioRoute : NavRoute<AudioCalViewModel> {

    override val route = Routes.Audio

    @Composable
    override fun viewModel(): AudioCalViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: AudioCalViewModel, data: Any?) = AudioPage(viewModel.apply {
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



