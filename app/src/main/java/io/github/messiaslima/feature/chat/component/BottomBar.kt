package io.github.messiaslima.feature.chat.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.messiaslima.R
import io.github.messiaslima.core.ui.theme.Magenta
import io.github.messiaslima.core.ui.theme.MagentaAlt
import io.github.messiaslima.core.ui.theme.MagentaChatTheme

@Composable
fun BottomBar(modifier: Modifier = Modifier, onSendMessageClicked: (String) -> Unit) {
    Surface(modifier = modifier, elevation = 12.dp) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var message by remember { mutableStateOf("") }

            MessageTextField(
                modifier = Modifier.weight(1f),
                value = message,
                onValueChange = { message = it },
                placeholder = stringResource(id = R.string.message_placeholder)
            )

            Spacer(modifier = Modifier.width(16.dp))

            SendButton { onSendMessageClicked(message) }
        }
    }
}

@Composable
fun SendButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(brush = Brush.linearGradient(colors = listOf(Magenta, MagentaAlt))),
        onClick = {
            Log.i("SendButton", "Clicked")
            onClick()
        },
    ) {
        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = "Send message",
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun BottomBarPreview() {
    MagentaChatTheme {
        BottomBar(modifier = Modifier.fillMaxWidth(), onSendMessageClicked = {})
    }
}
