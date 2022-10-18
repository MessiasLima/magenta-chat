package io.github.messiaslima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.github.messiaslima.feature.chat.ChatView
import io.github.messiaslima.ui.theme.MagentaChatTheme

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
