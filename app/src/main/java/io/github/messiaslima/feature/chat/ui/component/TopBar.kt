package io.github.messiaslima.feature.chat.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.messiaslima.R
import io.github.messiaslima.core.ui.theme.LightGray
import io.github.messiaslima.core.ui.theme.MagentaChatTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onNavigationItemClicked: () -> Unit,
    onMenuClicked: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 12.dp
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            IconButton(onClick = { onNavigationItemClicked() }) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "navigation icon",
                    tint = MaterialTheme.colors.primary
                )
            }

            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = R.drawable.woman),
                contentDescription = "Other user photo",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Sarah",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { onMenuClicked() }) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "Menu icon",
                    tint = LightGray
                )
            }
        }
    }
}

@Composable
@Preview
private fun TopBarPreview() {
    MagentaChatTheme {
        TopBar(onNavigationItemClicked = {}, onMenuClicked = {})
    }
}
