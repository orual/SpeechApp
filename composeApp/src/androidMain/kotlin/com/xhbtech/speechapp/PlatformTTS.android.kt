@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.xhbtech.speechapp

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale
import android.speech.tts.TextToSpeech as AndroidTextToSpeech

actual class TextToSpeech(private val textToSpeech: android.speech.tts.TextToSpeech) {

    actual val isSpeaking: Boolean
        get() = textToSpeech.isSpeaking
    actual fun stop() {
        textToSpeech.stop()
    }
    actual fun speak(text: String, queueMode: Int, params: Map<String, String>, audioFocus: String?) {
        val bundle = Bundle()
        textToSpeech.speak(text, queueMode, bundle, audioFocus)
    }


    actual companion object {
        actual val ACTION_TTS_QUEUE_PROCESSING_COMPLETED: String
            = AndroidTextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED
        actual val ERROR: Int
            = AndroidTextToSpeech.ERROR
        actual val ERROR_INVALID_REQUEST: Int
            = AndroidTextToSpeech.ERROR_INVALID_REQUEST
        actual val ERROR_NETWORK: Int
            = AndroidTextToSpeech.ERROR_NETWORK
        actual val ERROR_NETWORK_TIMEOUT: Int
            = AndroidTextToSpeech.ERROR_NETWORK_TIMEOUT
        actual val ERROR_NOT_INSTALLED_YET: Int
            = AndroidTextToSpeech.ERROR_NOT_INSTALLED_YET
        actual val ERROR_OUTPUT: Int
            = AndroidTextToSpeech.ERROR_OUTPUT
        actual val ERROR_SERVICE: Int
            = AndroidTextToSpeech.ERROR_SERVICE
        actual val ERROR_SYNTHESIS: Int
            = AndroidTextToSpeech.ERROR_SYNTHESIS
        actual val LANG_AVAILABLE: Int
            = AndroidTextToSpeech.LANG_AVAILABLE
        actual val LANG_COUNTRY_AVAILABLE: Int
            = AndroidTextToSpeech.LANG_COUNTRY_AVAILABLE
        actual val LANG_COUNTRY_VAR_AVAILABLE: Int
            = AndroidTextToSpeech.LANG_COUNTRY_VAR_AVAILABLE
        actual val LANG_MISSING_DATA: Int
            = AndroidTextToSpeech.LANG_MISSING_DATA
        actual val LANG_NOT_SUPPORTED: Int
            = AndroidTextToSpeech.LANG_NOT_SUPPORTED
        actual val QUEUE_ADD: Int
            = AndroidTextToSpeech.QUEUE_ADD
        actual val QUEUE_FLUSH: Int
            = AndroidTextToSpeech.QUEUE_FLUSH
        actual val STOPPED: Int
            = AndroidTextToSpeech.STOPPED
        actual val SUCCESS: Int
            = AndroidTextToSpeech.SUCCESS
    }

}

@Composable
actual fun rememberTextToSpeech(): MutableState<TextToSpeech?> {
    val context = LocalContext.current
    val androidTTS = remember { mutableStateOf<AndroidTextToSpeech?>(null) }
    val tts = remember { mutableStateOf<TextToSpeech?>(null) }
    DisposableEffect(context) {
        val textToSpeech = AndroidTextToSpeech(context) { status ->
            if (status == AndroidTextToSpeech.SUCCESS) {
                androidTTS.value?.language = Locale.getDefault()
            }
        }
        androidTTS.value = textToSpeech
        tts.value = TextToSpeech(textToSpeech)

        onDispose {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }
    return tts
}