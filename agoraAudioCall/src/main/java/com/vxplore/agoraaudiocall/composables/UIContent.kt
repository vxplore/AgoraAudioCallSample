package com.vxplore.agoraaudiocall.composables

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.vxplore.agoraaudiocall.AudioCalViewModel

@Composable
fun UIContent(viewModel: AudioCalViewModel) {
    BackHandler(true) {
        viewModel.onBackPressed()
    }
    UIRequirePermissions(
        permissions = viewModel.permissions,
        onPermissionGranted = {
            StartAgoraCalling(viewModel)
            CallingContent(viewModel)
        },
        onPermissionDenied = {
            AllowContent(it)
        }
    )
}






