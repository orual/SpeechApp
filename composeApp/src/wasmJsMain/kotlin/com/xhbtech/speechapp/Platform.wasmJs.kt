package com.xhbtech.speechapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()
actual val myLanguage: String?
    get() = TODO("Not yet implemented")

