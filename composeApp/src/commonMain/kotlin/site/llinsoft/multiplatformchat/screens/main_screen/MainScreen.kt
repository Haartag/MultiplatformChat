package site.llinsoft.multiplatformchat.screens.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import site.llinsoft.multiplatformchat.screens.chat_screen.ChatScreen

class MainScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    navigator.push(ChatScreen())
                }
            ) {
                Text("CHAT")
            }
        }
    }
}