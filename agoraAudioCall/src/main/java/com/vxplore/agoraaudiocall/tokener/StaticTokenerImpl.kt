package com.hellomydoc.videocall.tokener

import com.hellomydoc.videocall.models.CallTime
import com.hellomydoc.videocall.models.Ids
import com.vxplore.agoraaudiocall.tokener.Tokener

object StaticTokenerImpl: Tokener {
    override fun new(ids: Ids, callTime: CallTime): String {
        return "0061d4da708a96c43999be400b49d04c172IAB0Goyzo1cIwZSXfFsSAt5+MIe+8JeL8lzsX2s5MV3Ewu6WhZ8AAAAAEAAJmhJvAgVEYgEAAQACBURi"
    }
}