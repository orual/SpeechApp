package com.xhbtech.speechapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import speechapp.composeapp.generated.resources.Res
import speechapp.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val windowSizeClass = calculateWindowSizeClass()
        val history = remember { mutableStateListOf<String>() }
        var isSpeaking by remember { mutableStateOf(false) }
        val tts = rememberTextToSpeech()
        var textValue: String by remember { mutableStateOf("00.00") }
        val textStyle = MaterialTheme.typography.displayLarge.copy(
            fontFamily = FontFamily.Monospace,
            fontSize = when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> MaterialTheme.typography.displayLarge.fontSize * 2.5f
                WindowWidthSizeClass.Medium -> MaterialTheme.typography.displayLarge.fontSize * 5f
                WindowWidthSizeClass.Expanded -> MaterialTheme.typography.displayLarge.fontSize * 5f
                else -> {
                    MaterialTheme.typography.displayLarge.fontSize * 2.5f
                }
            },
            textAlign = TextAlign.Center,
        )
        Column(
            Modifier.fillMaxWidth().border(1.dp, Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.weight(1f))
            BasicTextField(
                value = textValue,
                onValueChange = { newText: String ->
                    textValue = newText
                },
                textStyle = textStyle,
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
                            textValue = ""
                            true
                        }
                        (it.key == Key.Enter && it.type == KeyEventType.KeyUp) -> {
                            textValue = ""
                            true
                        }
                        else -> false
                    }
                }
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

