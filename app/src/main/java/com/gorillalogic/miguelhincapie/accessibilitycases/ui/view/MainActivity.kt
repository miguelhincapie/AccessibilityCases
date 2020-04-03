package com.gorillalogic.miguelhincapie.accessibilitycases.ui.view

import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
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

    private fun onTalkBackStateChanged(isOn: Boolean) {
        if (isOn) {
            accessibility_state.text = getString(R.string.accessibility_state_on)
            accessibility_state.weakPostDelayed(400) { sendFocusToTitle() }
        } else {
            accessibility_state.text = getString(R.string.accessibility_state_off)
        }
    }

    private fun sendFocusToTitle() {
        with(accessibility_state) {
            requestFocus()
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        }
    }

    private fun onTurnOnButtonPressed() {
        talkBackViewModel.enableTalkBack()
    }

    private fun onTurnOFFButtonPressed() {
        talkBackViewModel.disableTalkBack()
    }
}
