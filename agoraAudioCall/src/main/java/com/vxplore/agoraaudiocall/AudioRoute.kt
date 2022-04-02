package com.vxplore.agoraaudiocall

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hellomydoc.videocall.navigation.NavRoute
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

@Composable
private fun AudioPage(viewModel: AudioCalViewModel) {
    UIRequirePermissions(
        permissions = viewModel.permissions,
        onPermissionGranted = {
            //CallScreen(viewModel)
            LaunchedEffect(Unit){
                viewModel.start()
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(viewModel.log){
                    Text(
                        it,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                item {

                }

            }

        },
        onPermissionDenied = {
            //AlertScreen(it)
            Button(onClick = {
                it()
            }) {
                Text("Allow")
            }

        }
    )

}

@Composable
fun UIRequirePermissions(
    permissions: Array<String>,
    onPermissionGranted: @Composable () -> Unit,
    onPermissionDenied: @Composable (requester: () -> Unit) -> Unit
) {
    val context = LocalContext.current

    var grantState by remember {
        mutableStateOf(permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        })
    }

    if (grantState) {
        onPermissionGranted()
    }
    else {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                grantState = !it.containsValue(false)
            }
        )
        onPermissionDenied {
            launcher.launch(permissions)
        }
    }
}