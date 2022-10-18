package io.github.messiaslima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.github.messiaslima.feature.chat.ui.ChatView
import io.github.messiaslima.core.ui.theme.MagentaChatTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagentaChatTheme {
                ChatView(onNavigationIconClicked = { finish() })
            }
        }
    }
}
