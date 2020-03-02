package com.gorillalogic.miguelhincapie.accessibilitycases.domain

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.intValue
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.toBoolean
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackState
import java.lang.ref.WeakReference

const val TALK_BACK_SERVICE_NAME = "com.google.android.marvin.talkback/.TalkBackService"
const val TALK_BACK_SERVICE_NAME_DISABLED = ""

object TalkBackFacade {

    lateinit var contextWeakReference: WeakReference<Context>

    fun isTalkBackEnabled() = Settings.Secure.getString(
        contextWeakReference.get()?.contentResolver,
        Settings.Secure.ACCESSIBILITY_ENABLED
    )?.toInt()?.toBoolean()
        ?: false

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
            TalkBackState.value = enable
            true
        } catch (e: SecurityException) {
            Log.e("AccessibilityHelper", e.message.toString())
            false
        }
    }

    fun enableTalkBack(): Boolean = if (!isTalkBackEnabled()) changeAccessibilityServicesState(
        true,
        TALK_BACK_SERVICE_NAME
    ) else false

    fun disableTalkBack(): Boolean = if (isTalkBackEnabled()) changeAccessibilityServicesState(
        false,
        TALK_BACK_SERVICE_NAME_DISABLED
    ) else false
}