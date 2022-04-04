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
fun AllowContent(requester: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                "Record audio permission required",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    requester()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text("Allow")
            }
        }
    }
}