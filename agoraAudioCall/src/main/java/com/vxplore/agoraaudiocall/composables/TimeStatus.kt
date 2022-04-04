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
import com.vxplore.agoraaudiocall.toTimeString

@Composable
fun TimeStatus(viewModel: AudioCalViewModel) {
    Row(

    ){
        Text(
            viewModel.elapsed.value.toTimeString,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            viewModel.left.value.toTimeString,
            color = Color.Red
        )
    }
}