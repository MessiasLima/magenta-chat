package io.github.messiaslima.feature.chat.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.messiaslima.core.ui.theme.LightBlue
import io.github.messiaslima.core.ui.theme.MagentaChatTheme
import io.github.messiaslima.feature.chat.usecase.OtherUserMessageUiModel

@Composable
fun OtherUserMessage(modifier: Modifier = Modifier, uiModel: OtherUserMessageUiModel) {
    val shape = if (uiModel.hasTail) {
        MaterialTheme.shapes.large.copy(bottomStart = CornerSize(2.dp))
    } else {
        MaterialTheme.shapes.large
    }

    Box(
        modifier = modifier.padding(vertical = 2.dp, horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(color = LightBlue)
                .padding(vertical = 4.dp, horizontal = 12.dp)
                .widthIn(max = 220.dp),
        ) {
            Text(text = uiModel.text, color = MaterialTheme.colors.onSurface)
        }
    }
}

@Composable
@Preview
private fun OtherUserMessagePreview() {
    MagentaChatTheme {
        Column {
            OtherUserMessage(
                uiModel = OtherUserMessageUiModel(
                    text = "My name is Messias",
                    hasTail = false
                )
            )
            OtherUserMessage(uiModel = OtherUserMessageUiModel(text = "And you?", hasTail = true))
        }
    }
}