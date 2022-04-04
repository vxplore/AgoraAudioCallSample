package com.vxplore.agoraaudiocall.composables

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.vxplore.agoraaudiocall.AudioCalViewModel

@Composable
fun HandleFinish(viewModel: AudioCalViewModel) {
    val context = LocalContext.current
    if(viewModel.leave.value){
        LaunchedEffect(key1 = Unit){
            (context as Activity).finish()
        }
    }
}