package site.llinsoft.multiplatformchat.screens.chat_screen

import site.llinsoft.multiplatformchat.model.ApiModel
import site.llinsoft.multiplatformchat.model.ChatItem
import site.llinsoft.multiplatformchat.model.ChatTemperature
import site.llinsoft.multiplatformchat.model.PromptItem
import site.llinsoft.multiplatformchat.model.PromptType

data class ChatUiState(
    val prompt: PromptItem = PromptItem(
        0,
        PromptType.CHAT,
        "",
        ApiModel.GPT35,
        ChatTemperature.MED.temperature
    ),
    val chatMessage: String = "",
    val errorText: String = "",
    val chatHistory: List<ChatItem> = emptyList(),
)
