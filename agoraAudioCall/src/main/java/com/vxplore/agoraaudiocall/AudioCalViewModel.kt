package com.vxplore.agoraaudiocall

import androidx.lifecycle.ViewModel
import com.hellomydoc.videocall.navigation.RouteNavigator
import com.vxplore.agoraaudiocall.tokener.Tokener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioCalViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
) : LifeCycleAwareDelegate, ViewModel(), RouteNavigator by routeNavigator {
}