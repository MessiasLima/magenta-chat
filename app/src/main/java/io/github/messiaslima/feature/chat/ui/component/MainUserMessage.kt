package io.github.messiaslima.feature.chat.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.messiaslima.core.ui.theme.Magenta
import io.github.messiaslima.core.ui.theme.MagentaAlt
import io.github.messiaslima.core.ui.theme.MagentaChatTheme
import io.github.messiaslima.feature.chat.usecase.MainUserMessageUiModel

@Composable
fun MainUserMessage(modifier: Modifier = Modifier, uiModel: MainUserMessageUiModel) {

    val shape = if (uiModel.hasTail) {
        MaterialTheme.shapes.large.copy(bottomEnd = CornerSize(2.dp))
    } else {
        MaterialTheme.shapes.large
    }

    Box(
        modifier = modifier.padding(vertical = 2.dp, horizontal = 12.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(Brush.linearGradient(colors = listOf(Magenta, MagentaAlt)))
                .padding(vertical = 4.dp, horizontal = 12.dp),
        ) {
            Text(text = uiModel.text, color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Composable
@Preview
fun MainUserMessagePreview() {
    MagentaChatTheme {
        Column {
            MainUserMessage(
                uiModel = MainUserMessageUiModel(
                    text = "My name is Messias",
                    hasTail = false
                )
            )

            MainUserMessage(
                uiModel = MainUserMessageUiModel(
                    text = "And you?",
                    hasTail = true
                )
            )
        }
    }
}