package com.gorillalogic.miguelhincapie.accessibilitycases.domain

/**
 * Copyright 2020 [Miguel Hincapie C]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * https://stackoverflow.com/users/1332549/miguelhincapiec
 * https://github.com/miguelhincapie
 * https://www.linkedin.com/in/miguelhincapie/
 */
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.util.intValue
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.util.toBoolean
import java.lang.ref.WeakReference
import javax.inject.Inject

private const val TALK_BACK_SERVICE_NAME = "com.google.android.marvin.talkback/.TalkBackService"
private const val TALK_BACK_SERVICE_NAME_DISABLED = ""

class TalkBackFacade @Inject constructor(private val contextWeakReference: WeakReference<Context>) {

    fun isTalkBackEnabled() = Settings.Secure.getString(
        contextWeakReference.get()?.contentResolver,
        Settings.Secure.ACCESSIBILITY_ENABLED
    )?.toInt()?.toBoolean()
        ?: false

    fun enableTalkBack() = changeAccessibilityServicesState(
        true,
        TALK_BACK_SERVICE_NAME
    )

    fun disableTalkBack() = changeAccessibilityServicesState(
        false,
        TALK_BACK_SERVICE_NAME_DISABLED
    )

    private fun changeAccessibilityServicesState(
        enable: Boolean,
        accessibilityServiceName: String
    ): Boolean {
        return try {
            Settings.Secure.putString(
                contextWeakReference.get()?.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
                accessibilityServiceName
            )
            Settings.Secure.putString(
                contextWeakReference.get()?.contentResolver,
                Settings.Secure.ACCESSIBILITY_ENABLED,
                enable.intValue().toString()
            )
            true
        } catch (e: SecurityException) {
            Log.e("AccessibilityHelper", e.message.toString())
            false
        }
    }
}