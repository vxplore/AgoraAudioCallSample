package com.vxplore.agoraaudiocall

import androidx.lifecycle.Lifecycle

interface LifeCycleAwareDelegate{
    fun onCreate()
    fun onStart()
    fun onResume()
    fun onPause(isChangingConfiguration: Boolean = false)
    fun onStop()
    fun onDestroy(isChangingConfiguration: Boolean = false)
    fun onAnyLifecycleEvent(event: Lifecycle.Event)
}