package com.vxplore.agoraaudiocall

import android.Manifest
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellomydoc.videocall.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioCalViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val agoraAudioCall: AgoraAudioCall,
) : LifeCycleAwareDelegate, ViewModel(), RouteNavigator by routeNavigator {
    val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO
    )
    val log = mutableStateListOf("No log")
    init {

    }
    fun setData(data: Any?) {

    }
    override fun onCreate() {

    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause(isChangingConfiguration: Boolean) {

    }

    override fun onStop() {

    }

    override fun onDestroy(isChangingConfiguration: Boolean) {

    }

    override fun onAnyLifecycleEvent(event: Lifecycle.Event) {

    }

    fun start() {
        viewModelScope.launch {
            agoraAudioCall.create{s,s1->
                log.add(s1)
            }
            agoraAudioCall.joinChannel()
        }
    }
}