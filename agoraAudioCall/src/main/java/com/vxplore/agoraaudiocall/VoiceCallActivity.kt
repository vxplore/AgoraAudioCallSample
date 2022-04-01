package com.vxplore.agoraaudiocall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hellomydoc.videocall.navigation.NavigationComponent
import com.vxplore.agoraaudiocall.ui.theme.AgoraAudioCallSampleTheme

class VoiceCallActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            navController = rememberAnimatedNavController()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Black,
                    darkIcons = false
                )
            }
            NavigationComponent(navController)
        }
    }
}
