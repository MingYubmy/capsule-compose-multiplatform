package dev.mingyubmy.capsule

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "capsule-compose-multiplatform",
    ) {
        App()
    }
}