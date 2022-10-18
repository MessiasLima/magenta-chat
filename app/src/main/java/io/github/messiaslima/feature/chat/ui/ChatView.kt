package io.github.messiaslima.feature.chat.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.messiaslima.R
import io.github.messiaslima.core.database.message.Sender
import io.github.messiaslima.core.ui.theme.MagentaChatTheme
import io.github.messiaslima.feature.chat.ui.component.*
import io.github.messiaslima.feature.chat.usecase.ChatUiModel
import io.github.messiaslima.feature.chat.usecase.MainUserMessageUiModel
import io.github.messiaslima.feature.chat.usecase.OtherUserMessageUiModel
import io.github.messiaslima.feature.chat.usecase.SectionUiModel

@Composable
fun ChatView(viewModel: ChatViewModel = viewModel(), onNavigationIconClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val sender by viewModel.sender.collectAsState()
        val messages by viewModel.messages.collectAsState(initial = emptyList())

        TopBar(
            onNavigationItemClicked = onNavigationIconClicked,
            onMenuClicked = { viewModel.toggleSender() },
        )

        LazyColumn(modifier = Modifier.weight(1f), reverseLayout = true) {
            items(messages) { item ->
                ChatItem(item)
            }
        }

        AnimatedVisibility(visible = sender == Sender.OTHER_USER) {
            UserIndicator()
        }

        BottomBar(modifier = Modifier.fillMaxWidth(), onSendMessageClicked = {
            viewModel.sendMessage(it)
        })
    }
}

@Composable
private fun UserIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.user_indicator),
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
private fun ChatItem(item: ChatUiModel) {
    when (item) {
        is MainUserMessageUiModel -> MainUserMessage(
            modifier = Modifier.fillMaxWidth(),
            uiModel = item
        )

        is OtherUserMessageUiModel -> OtherUserMessage(
            modifier = Modifier.fillMaxWidth(),
            uiModel = item,
        )

        is SectionUiModel -> Section(
            modifier = Modifier.fillMaxWidth(),
            sectionUiModel = item,
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ChatViewPreview() {
    MagentaChatTheme {
        ChatView(onNavigationIconClicked = {})
    }
}
