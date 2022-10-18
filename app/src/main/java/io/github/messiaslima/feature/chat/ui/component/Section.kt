package io.github.messiaslima.feature.chat.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.messiaslima.core.ui.theme.LightGray
import io.github.messiaslima.core.ui.theme.MagentaChatTheme
import io.github.messiaslima.feature.chat.usecase.SectionUiModel

@Composable
fun Section(modifier: Modifier = Modifier, sectionUiModel: SectionUiModel) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = sectionUiModel.text,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Medium,
            color = LightGray,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SectionPreview() {
    MagentaChatTheme {
        Section(
            modifier = Modifier.fillMaxWidth(),
            sectionUiModel = SectionUiModel("Today at 12:34")
        )
    }
}