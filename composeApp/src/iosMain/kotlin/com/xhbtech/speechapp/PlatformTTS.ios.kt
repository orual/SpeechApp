@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.xhbtech.speechapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

actual class TextToSpeech {
    actual val isSpeaking: Boolean
        get() = TODO("Not yet implemented")

    actual fun stop() {
    }

    actual fun speak(
        text: String,
        queueMode: Int,
        params: Map<String, String>,
        audioFocus: String?
    ) {
    }

    actual companion object {
        actual val ACTION_TTS_QUEUE_PROCESSING_COMPLETED: String
            get() = TODO("Not yet implemented")
        actual val ERROR: Int
            get() = TODO("Not yet implemented")
        actual val ERROR_INVALID_REQUEST: Int
            get() = TODO("Not yet implemented")
        actual val ERROR_NETWORK: Int
            get() = TODO("Not yet implemented")
        actual val ERROR_NETWORK_TIMEOUT: Int
            get() = TODO("Not yet implemented")
        actual val ERROR_NOT_INSTALLED_YET: Int
            get() = TODO("Not yet implemented")
        actual val ERROR_OUTPUT: Int
            get() = TODO("Not yet implemented")
        actual val ERROR_SERVICE: Int
            get() = TODO("Not yet implemented")
        actual val ERROR_SYNTHESIS: Int
            get() = TODO("Not yet implemented")
        actual val LANG_AVAILABLE: Int
            get() = TODO("Not yet implemented")
        actual val LANG_COUNTRY_AVAILABLE: Int
            get() = TODO("Not yet implemented")
        actual val LANG_COUNTRY_VAR_AVAILABLE: Int
            get() = TODO("Not yet implemented")
        actual val LANG_MISSING_DATA: Int
            get() = TODO("Not yet implemented")
        actual val LANG_NOT_SUPPORTED: Int
            get() = TODO("Not yet implemented")
        actual val QUEUE_ADD: Int
            get() = TODO("Not yet implemented")
        actual val QUEUE_FLUSH: Int
            get() = TODO("Not yet implemented")
        actual val STOPPED: Int
            get() = TODO("Not yet implemented")
        actual val SUCCESS: Int
            get() = TODO("Not yet implemented")

    }

}

@Composable
actual fun rememberTextToSpeech(): MutableState<TextToSpeech?> {
    TODO("Not yet implemented")
}