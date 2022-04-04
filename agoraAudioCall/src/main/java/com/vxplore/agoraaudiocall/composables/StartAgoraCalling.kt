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
fun StartAgoraCalling(viewModel: AudioCalViewModel) {
    LaunchedEffect(Unit){
        viewModel.start()
    }
}