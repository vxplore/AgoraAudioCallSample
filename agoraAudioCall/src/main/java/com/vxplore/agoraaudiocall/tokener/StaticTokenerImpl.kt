package com.vxplore.agoraaudiocall.tokener

import com.vxplore.agoraaudiocall.Credential
import com.vxplore.agoraaudiocall.TokenBuilder
import com.vxplore.agoraaudiocall.tokener.Tokener

class StaticTokenerImpl(credential: Credential, tokenBuilder: TokenBuilder) : Tokener {
    override suspend fun new(): String {
        return "00613622905826d46538beb9bd1d96c83b1IADLjTMOaOqkNsuH2c3M3/ac1D0TjIMYGOrvl/seQ8VY1tm4ZkoAAAAAEABrDG+dZ3tJYgEAAQBne0li"
    }
}