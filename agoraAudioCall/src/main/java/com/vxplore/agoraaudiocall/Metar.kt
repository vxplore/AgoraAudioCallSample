package com.vxplore.agoraaudiocall

interface Metar {
    operator fun get(key: String): String
}