package com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.intValue
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.toBoolean
import java.lang.ref.WeakReference

private const val TALK_BACK_SERVICE_NAME = "com.google.android.marvin.talkback/.TalkBackService"
private const val TALK_BACK_SERVICE_NAME_DISABLED = ""

class TalkBackFacade(private val contextWeakReference: WeakReference<Context>) {

    /**
     * @return the state of the service: <code>true</code> if it's ON, <code>false</code> otherwise.
     */
    fun switchTalkBackState() = if (isTalkBackEnabled()) !disableTalkBack() else enableTalkBack()

    fun isTalkBackEnabled() = Settings.Secure.getString(
        contextWeakReference.get()?.contentResolver,
        Settings.Secure.ACCESSIBILITY_ENABLED
    )?.toInt()?.toBoolean()
        ?: false

    private fun enableTalkBack() =
        changeAccessibilityServicesState(true, TALK_BACK_SERVICE_NAME)

    private fun disableTalkBack() =
        changeAccessibilityServicesState(false, TALK_BACK_SERVICE_NAME_DISABLED)

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