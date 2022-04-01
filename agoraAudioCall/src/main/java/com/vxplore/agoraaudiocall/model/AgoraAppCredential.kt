package com.vxplore.agoraaudiocall.model

import java.security.cert.Certificate

data class AgoraAppCredential(
    val appId: String,
    val appCertificate: String
)