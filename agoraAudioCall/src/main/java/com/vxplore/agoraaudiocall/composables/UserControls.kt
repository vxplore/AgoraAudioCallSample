package com.vxplore.agoraaudiocall.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
        FloatingActionButton(
            modifier = Modifier,
            onClick = {
                viewModel.callMuteLocal()
            },
            backgroundColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = "Call End",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun CallEndControl(viewModel: AudioCalViewModel) {
    FloatingActionButton(
        modifier = Modifier,
        onClick = {
            viewModel.callEnd()
        },
        backgroundColor = Color.Red
    ) {
        Icon(
            imageVector = Icons.Filled.Call,
            contentDescription = "Call End",
            tint = Color.White
        )
    }
}
