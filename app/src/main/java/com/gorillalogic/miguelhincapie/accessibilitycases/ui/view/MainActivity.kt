package com.gorillalogic.miguelhincapie.accessibilitycases.ui.view

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
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.util.weakPostDelayed
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackViewModel
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var talkBackViewModelFactory: TalkBackViewModelFactory
    private val talkBackViewModel: TalkBackViewModel by viewModels { talkBackViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        talkBackViewModel.talkBackStateLiveData().observe(this) { onTalkBackStateChanged(it) }
        button_turn_on.setOnClickListener { onTurnOnButtonPressed() }
        button_turn_off.setOnClickListener { onTurnOFFButtonPressed() }
        button_send_focus.setOnClickListener { sendFocusToTitle() }
    }

    /**
     * With this function we are observing a specific accessibility service started/stopped: TalkBack
     */
    private fun onTalkBackStateChanged(isOn: Boolean) {
        if (isOn) {
            accessibility_state.text = getString(R.string.accessibility_state_on)
            accessibility_state.weakPostDelayed { sendFocusToTitle() }
        } else {
            accessibility_state.text = getString(R.string.accessibility_state_off)
        }
    }

    /**
     * Observe for any accessibility service state change. Use [AccessibilityManager] to list
     * current accessibility service ON or send [AccessibilityEvent] like a speech out.
     */
    private fun listenForAccessibilityServiceStatus(context: Context) {
        val accessibilityManager =
            context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        accessibilityManager.addAccessibilityStateChangeListener { enabled ->
            // Do your stuff
        }
    }

    private fun onTurnOnButtonPressed() {
        talkBackViewModel.enableTalkBack()
    }

    private fun onTurnOFFButtonPressed() {
        accessibility_state.clearFocus()
        talkBackViewModel.disableTalkBack()
    }

    private fun sendFocusToTitle() = with(accessibility_state) {
        requestFocus()
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
    }
}
