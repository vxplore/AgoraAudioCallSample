package com.vxplore.agoraaudiocall.composables

import android.widget.Space
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
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
    val color = animateColorAsState(
        targetValue = if(viewModel.peerOnline.value) Color.Black else Color.Gray,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearOutSlowInEasing
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color.value
            )
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
            Text(
                if(viewModel.peerOnline.value) "Peer Online" else "Peer Offline",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            TimeStatus(viewModel)
            UserControls(viewModel)
        }
    }
}




