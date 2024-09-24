package com.xhbtech.speechapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val myLanguage: String?
