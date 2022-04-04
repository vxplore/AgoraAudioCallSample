package com.vxplore.agoraaudiocall.composables

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.MicOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.vxplore.agoraaudiocall.AudioCalViewModel
import com.vxplore.agoraaudiocall.AudioVisualizer
import com.vxplore.agoraaudiocall.toTimeString

@Composable
fun CallingContent(viewModel: AudioCalViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        /*val context = LocalContext.current
        val visualizer by remember {
            mutableStateOf(AudioVisualizer(context))
        }
        AndroidView(
            factory = {
                visualizer
            },
            update = { view ->
                view.bytes = viewModel.bytes.value
            },
            modifier = Modifier.fillMaxSize()
        )*/
        Icon(
            imageVector = if(viewModel.remoteAudio.value) Icons.Outlined.Mic else Icons.Outlined.MicOff,
            tint = Color.Gray,
            contentDescription = "Remote audio",
            modifier = Modifier.align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeStatus(viewModel)
            UserControls(viewModel)
        }
    }
}




