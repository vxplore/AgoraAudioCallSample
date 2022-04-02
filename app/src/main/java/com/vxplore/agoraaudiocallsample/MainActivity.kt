package com.vxplore.agoraaudiocallsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.vxplore.agoraaudiocall.CallBox
import com.vxplore.agoraaudiocallsample.ui.theme.AgoraAudioCallSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgoraAudioCallSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row(){
                        Button(onClick = {
                            CallBox.start(
                                "client",
                                "doctor",
                                "client_doctor",
                                this@MainActivity
                            )
                        }) {
                            Text("As Client")
                        }

                        Button(onClick = {
                            CallBox.start(
                                "doctor",
                                "client",
                                "client_doctor",
                                this@MainActivity
                            )
                        }) {
                            Text("As Doctory")
                        }
                    }
                }
            }
        }
    }
}