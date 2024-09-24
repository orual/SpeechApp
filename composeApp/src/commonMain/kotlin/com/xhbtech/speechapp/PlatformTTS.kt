@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.xhbtech.speechapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

expect class TextToSpeech{

    val isSpeaking: Boolean
    fun stop()
    fun speak(text: String, queueMode: Int, params:Map<String, String>, audioFocus: String?)

    companion object {

        val ACTION_TTS_QUEUE_PROCESSING_COMPLETED: String
        // "android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED"
        val ERROR: Int // -1
        val ERROR_INVALID_REQUEST: Int // -8
        val ERROR_NETWORK: Int // -6
        val ERROR_NETWORK_TIMEOUT: Int // -7
        val ERROR_NOT_INSTALLED_YET: Int // -9
        val ERROR_OUTPUT: Int // -5
        val ERROR_SERVICE: Int // -4
        val ERROR_SYNTHESIS: Int // -3
        val LANG_AVAILABLE: Int // 0
        val LANG_COUNTRY_AVAILABLE: Int // 1
        val LANG_COUNTRY_VAR_AVAILABLE: Int // 2
        val LANG_MISSING_DATA: Int // -1
        val LANG_NOT_SUPPORTED: Int // -2
        val QUEUE_ADD: Int // 1
        val QUEUE_FLUSH: Int // 0
        val STOPPED: Int // -2
        val SUCCESS: Int // 0

    }
}

@Composable
expect fun rememberTextToSpeech(): MutableState<TextToSpeech?>