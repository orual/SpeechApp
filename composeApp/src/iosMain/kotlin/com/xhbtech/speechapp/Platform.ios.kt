package com.xhbtech.speechapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual val myLanguage: String?
    get() = TODO("Not yet implemented")

