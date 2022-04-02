package com.vxplore.agoraaudiocall

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MetarImpl @Inject constructor(
    private val  application: Context
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