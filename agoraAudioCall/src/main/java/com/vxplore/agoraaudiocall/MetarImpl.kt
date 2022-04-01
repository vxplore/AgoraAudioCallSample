package com.vxplore.agoraaudiocall

import android.app.Application
import android.content.pm.PackageManager
import javax.inject.Inject

class MetarImpl @Inject constructor(
    private val application: Application
): Metar {
    override fun get(key: String): String {
        return try {
            val ai = application
                .packageManager
                .getApplicationInfo(
                    application
                        .packageName,
                    PackageManager.GET_META_DATA
                )
            val bundle = ai.metaData
            bundle.getString(key)?:""
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }
    }
}