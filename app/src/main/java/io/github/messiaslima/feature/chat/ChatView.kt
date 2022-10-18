package io.github.messiaslima.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.messiaslima.core.ui.theme.MagentaChatTheme
import io.github.messiaslima.feature.chat.component.BottomBar
import io.github.messiaslima.feature.chat.component.TopBar

@Composable
fun ChatView(viewModel: ChatViewModel = viewModel(), onNavigationIconClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val sender by viewModel.sender.collectAsState()

        TopBar(onNavigationItemClicked = onNavigationIconClicked, onMenuClicked = { viewModel.toggleSender() })

        Spacer(modifier = Modifier.weight(1f))

        BottomBar(modifier = Modifier.fillMaxWidth(), onSendMessageClicked = {
            viewModel.sendMessage(it)
        })
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ChatViewPreview() {
    MagentaChatTheme {
        ChatView(onNavigationIconClicked = {})
    }
}
