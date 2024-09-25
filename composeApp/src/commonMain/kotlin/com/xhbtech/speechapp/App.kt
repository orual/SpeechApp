package com.xhbtech.speechapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val windowSizeClass = calculateWindowSizeClass()
        val history = remember { mutableStateListOf<String>() }
        var isSpeaking by remember { mutableStateOf(false) }
        val tts = rememberTextToSpeech()
        var textValue: String by remember { mutableStateOf("") }
        var prevTextValue: String by remember { mutableStateOf("00.00") }
        val textStyle = MaterialTheme.typography.displayLarge.copy(
            fontFamily = FontFamily.Monospace,
            fontSize = when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> MaterialTheme.typography.displayLarge.fontSize * 2.2f
                WindowWidthSizeClass.Medium -> MaterialTheme.typography.displayLarge.fontSize * 5f
                WindowWidthSizeClass.Expanded -> MaterialTheme.typography.displayLarge.fontSize * 5f
                else -> {
                    MaterialTheme.typography.displayLarge.fontSize * 2.2f
                }
            },
            textAlign = TextAlign.Center,
        )
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.weight(1f))
            CompositionLocalProvider(
                // You can also provides null to completely disable the default input service.
                LocalTextInputService provides null
            ) {
                val focusRequester = remember { FocusRequester() }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
                OutlinedTextField(
                    value = textValue,
                    onValueChange = { newText: String ->
                        textValue = newText
                    },
                    textStyle = textStyle,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            text = prevTextValue,
                            style = textStyle,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },

                    modifier = Modifier.onPreviewKeyEvent {
                        when {
                            (it.key == Key.Enter && it.type == KeyEventType.KeyUp) -> {
                                history.add(textValue)
                                if (tts.value?.isSpeaking == true) {
                                    tts.value?.stop()
                                    isSpeaking = false
                                } else {
                                    tts.value?.speak(
                                        textValue, TextToSpeech.QUEUE_FLUSH, emptyMap(), ""
                                    )
                                    isSpeaking = true
                                }
                                prevTextValue = textValue
                                textValue = ""
                                true
                            }
                            (it.key == Key.Enter && it.type == KeyEventType.KeyUp) -> {
                                true
                            }
                            else -> false
                        }
                    }.focusRequester(focusRequester)
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

