package com.vxplore.agoraaudiocall.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.MicOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vxplore.agoraaudiocall.AudioCalViewModel

@Composable
fun UserControls(viewModel: AudioCalViewModel) {
    Row(
        modifier = Modifier.wrapContentWidth().padding(24.dp)
    ){
        CallEndControl(viewModel)
        ToggleLocalAudioControl(viewModel)
        FloatingActionButton(
            modifier = Modifier.padding(horizontal = 4.dp),
            onClick = {
                viewModel.toggleRemoteAudio()
            },
            backgroundColor = Color.Red
        ) {
            Icon(
                imageVector = if(viewModel.remoteAudioState.value) Icons.Filled.VolumeUp else Icons.Filled.VolumeMute,
                contentDescription = "Toggle Remote Audio",
                tint = Color.White
            )
        }
    }
}



