package com.vxplore.agoraaudiocall

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.hellomydoc.videocall.navigation.RouteNavigator
import com.vxplore.agoraaudiocall.tokener.Tokener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioCalViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    //private val agoraAudioCall: AgoraAudioCall,
    private val metar: Metar
) : LifeCycleAwareDelegate, ViewModel(), RouteNavigator by routeNavigator {
    init {
        /*viewModelScope.launch {
            agoraAudioCall.create()
            agoraAudioCall.joinChannel()
        }*/
        val d = metar["jkl"]
        Log.d("fljfsdlf",d)
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
}