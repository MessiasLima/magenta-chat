package io.github.messiaslima.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.messiaslima.ui.theme.MagentaChatTheme

@Composable
fun ChatView() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(onNavigationItemClicked = {}, onMenuClicked = {})
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ChatViewPreview() {
    MagentaChatTheme {
        ChatView()
    }
}
