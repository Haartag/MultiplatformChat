package site.llinsoft.multiplatformchat.screens.prompt_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.skeptick.libres.compose.painterResource
import site.llinsoft.multiplatformchat.Res
import site.llinsoft.multiplatformchat.model.PromptItem
import site.llinsoft.multiplatformchat.model.toPromptItem
import site.llinsoft.multiplatformchat.screens.chat_screen.ChatScreen
import site.llinsoft.multiplatformchat.utils.DefaultPrompts

class PromptScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DefaultPrompts.defaults.forEach {
                PromptTile(it.toPromptItem(), {}, {})
            }
        }
    }

    @Composable
    private fun PromptTile(
        promptItem: PromptItem,
        onCardClick: () -> Unit,
        onDeleteClick: (PromptItem) -> Unit,
    ) {
        Card(
            modifier = Modifier
                .height(144.dp)
                .fillMaxWidth()
                .clickable {
                    onCardClick()
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(image = promptItem.type.icon),
                            contentDescription = promptItem.type.name
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = promptItem.model.label,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete prompt",
                        modifier = Modifier.clickable {
                            onDeleteClick(promptItem)
                        }

                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "\"${promptItem.prompt}\"",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

