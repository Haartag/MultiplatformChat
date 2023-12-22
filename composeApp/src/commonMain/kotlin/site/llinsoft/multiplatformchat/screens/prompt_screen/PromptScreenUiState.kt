package site.llinsoft.multiplatformchat.screens.prompt_screen

import site.llinsoft.multiplatformchat.model.PromptItem
import site.llinsoft.multiplatformchat.model.PromptType

data class PromptUiState(
    val selectedTab: Int = 0,
    val searchText: String = "",
    val promptCards: List<PromptItem> = emptyList(),
) {
    val tabs = PromptType.entries.toTypedArray()
    private val selectedPromptType = tabs[selectedTab]
    val filteredPromptCards = promptCards.filter { it.type == selectedPromptType }
}