package com.vxplore.agoraaudiocall.tokener

import com.vxplore.agoraaudiocall.tokener.Tokener

class StaticTokenerImpl: Tokener {
    override suspend fun new(): String {
        return "0061d4da708a96c43999be400b49d04c172IAB0Goyzo1cIwZSXfFsSAt5+MIe+8JeL8lzsX2s5MV3Ewu6WhZ8AAAAAEAAJmhJvAgVEYgEAAQACBURi"
    }
}