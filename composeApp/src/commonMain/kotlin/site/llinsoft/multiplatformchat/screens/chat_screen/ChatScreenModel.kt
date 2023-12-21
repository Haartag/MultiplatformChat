package site.llinsoft.multiplatformchat.screens.chat_screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import site.llinsoft.multiplatformchat.domain.OpenAiManager
import site.llinsoft.multiplatformchat.model.ChatItem
import site.llinsoft.multiplatformchat.model.SenderType
import site.llinsoft.multiplatformchat.model.getChatTemperature
import site.llinsoft.multiplatformchat.model.toPromptItem
import site.llinsoft.multiplatformchat.utils.DefaultPrompts
import site.llinsoft.multiplatformchat.utils.Resource

class ChatScreenModel: ScreenModel {

    private val openAiManager = OpenAiManager()

    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState())
    val uiState = _uiState.asStateFlow()

    private fun setPrompt() {
        val prompt = DefaultPrompts.defaults[4].toPromptItem()
        _uiState.update {
            it.copy(
                prompt = prompt,
                chatHistory = _uiState.value.chatHistory + ChatItem(
                    text = prompt.prompt,
                    senderType = SenderType.SYSTEM
                )
            )
        }
    }

    init {
        setPrompt()
    }

    fun updateText(newText: String) {
        _uiState.update {
            it.copy(chatMessage = newText)
        }
    }

    private fun updateError(error: String = "") {
        _uiState.update {
            it.copy(
                errorText = error
            )
        }
    }

    private fun addMessage(message: ChatItem) {
        _uiState.update {
            it.copy(
                chatHistory = it.chatHistory + message,
                chatMessage = if (message.senderType == SenderType.USER) {
                    ""
                } else {
                    _uiState.value.chatMessage
                }
            )
        }
    }

    fun sendMessage() {
        if (_uiState.value.chatMessage.isNotEmpty()) {
            addMessage(
                ChatItem(
                    text = _uiState.value.chatMessage,
                    senderType = SenderType.USER
                )
            )
            getChatAnswer(
                onSuccess = { result ->
                    addMessage(
                        ChatItem(
                            text = result,
                            senderType = SenderType.CHAT
                        )
                    )
                },
                onError = { result ->
                    updateError(result)
                    addMessage(
                        ChatItem(
                            text = result,
                            senderType = SenderType.SYSTEM,
                            inError = true
                        )
                    )
                }

            )
        }
    }

    private fun getChatAnswer(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        screenModelScope.launch {
            val response = openAiManager.askForText(
                system = _uiState.value.chatHistory.first().text,
                request = _uiState.value.chatHistory.last().text,
                model = _uiState.value.prompt.model,
                temperature = _uiState.value.prompt.temperature.getChatTemperature()
            )

            when (response) {
                is Resource.Success -> {
                    val result = response.data.choices[0].message.content
                    result?.let {
                        onSuccess(it)
                    }
                }
                is Resource.Error -> {
                    //Log.d("ChatTag", "getChatAnswer: ", response.exception)
                    //onError(errorToTextConverter.convertChatException(response.exception))
                }
            }
        }
    }
}