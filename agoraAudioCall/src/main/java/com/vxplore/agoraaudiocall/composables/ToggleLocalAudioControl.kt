package com.vxplore.agoraaudiocall.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.MicOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vxplore.agoraaudiocall.AudioCalViewModel


@Composable
fun ToggleLocalAudioControl(viewModel: AudioCalViewModel) {
    FloatingActionButton(
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = {
            viewModel.callToggleLocal()
        },
        backgroundColor = Color.White
    ) {
        Icon(
            imageVector = if(viewModel.localAudioState.value) Icons.Outlined.Mic else Icons.Outlined.MicOff,
            contentDescription = "Toggle Local Audio Mute",
            tint = Color.Black
        )
    }
}