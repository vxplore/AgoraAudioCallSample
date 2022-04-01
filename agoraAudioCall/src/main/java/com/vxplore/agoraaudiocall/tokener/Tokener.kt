package com.vxplore.agoraaudiocall.tokener


interface Tokener {
    suspend fun new(): String
}