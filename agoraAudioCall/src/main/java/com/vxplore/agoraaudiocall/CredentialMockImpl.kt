package com.vxplore.agoraaudiocall

class CredentialMockImpl: Credential {
    override suspend fun appId(): String {
        return "13622905826d46538beb9bd1d96c83b1"
    }
}