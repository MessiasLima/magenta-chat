package io.github.messiaslima.feature.chat.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.messiaslima.core.ui.theme.LightGray
import io.github.messiaslima.core.ui.theme.MagentaChatTheme

@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String = ""
) {
    var focused by remember {
        mutableStateOf(false)
    }

    val decorationColor = if (focused) MaterialTheme.colors.primary else LightGray

    val textFieldShape = RoundedCornerShape(24.dp)

    BasicTextField(
        modifier = modifier.onFocusChanged { focused = it.hasFocus },
        value = value,
        onValueChange = onValueChange,
        decorationBox = { content ->
            Box(
                modifier = modifier
                    .padding(vertical = 8.dp)
                    .clip(textFieldShape)
                    .border(width = 1.dp, shape = textFieldShape, color = decorationColor)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                if (value.isEmpty()) {
                    Placeholder(text = placeholder)
                }

                content()
            }
        },
        textStyle = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Medium, fontSize = 18.sp
        ),
    )
}

@Composable
private fun Placeholder(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        color = LightGray,
    )
}

@Composable
@Preview(showBackground = true)
private fun MessageTextFieldPreview() {
    MagentaChatTheme {
        MessageTextField(value = "Sample message")
    }
}

@Composable
@Preview(showBackground = true)
private fun MessageTextFieldPreviewPlaceholder() {
    MagentaChatTheme {
        MessageTextField(value = "", placeholder = "Sample placeholder")
    }
}
