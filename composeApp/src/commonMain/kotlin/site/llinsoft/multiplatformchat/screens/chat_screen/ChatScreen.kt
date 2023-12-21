package site.llinsoft.multiplatformchat.screens.chat_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import site.llinsoft.multiplatformchat.ScreenA
import site.llinsoft.multiplatformchat.model.SenderType

class ChatScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { ChatScreenModel() }

        val uiState by screenModel.uiState.collectAsState()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = uiState.prompt.type.name)
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Go back"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    items(uiState.chatHistory) { chatItem ->
                        ChatTile(
                            sender = chatItem.senderType,
                            text = chatItem.text,
                            //tileWidth = tileWidth,
                            inError = chatItem.inError
                        )
                    }
                }
                ChatTextField(
                    message = uiState.chatMessage,
                    //enabled = uiState.errorText.isEmpty(),
                    onSentClick = screenModel::sendMessage,
                    onMessageChange = { screenModel.updateText(it) }
                )
            }
        }
    }
}

@Composable
private fun ChatTextField(
    message: String,
    //enabled: Boolean,
    onSentClick: () -> Unit,
    onMessageChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.TopCenter
    ) {
        Row {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(32.dp)
                    ),
                value = message,
                onValueChange = { onMessageChange(it) },
                shape = RoundedCornerShape(32.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = { onSentClick() }
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        onSentClick()
                    }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                    }
                },
                //enabled = enabled
            )
        }
    }
}

@Composable
fun ChatTile(
    sender: SenderType,
    text: String,
    //tileWidth: Dp,
    inError: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (sender == SenderType.USER) Alignment.TopEnd else Alignment.TopStart
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 40f,
                        topEnd = 40f,
                        bottomStart = if (sender == SenderType.USER) 40f else 0f,
                        bottomEnd = if (sender == SenderType.USER) 0f else 40f,
                    )
                )
                //.widthIn(16.dp, tileWidth)
                .background(
                    if (sender == SenderType.USER) {
                        MaterialTheme.colorScheme.tertiaryContainer
                    } else MaterialTheme.colorScheme.primaryContainer
                )
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(if (sender == SenderType.USER) Alignment.End else Alignment.Start),
                    text = "${
                        sender.name.lowercase().replaceFirstChar { it.titlecase() }
                    }:",
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = text,
                    color = if (inError) MaterialTheme.colorScheme.error else Color.Unspecified
                )
            }
        }
    }
}