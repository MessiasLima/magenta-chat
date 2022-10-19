package io.github.messiaslima.feature.chat.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.messiaslima.MainActivity
import io.github.messiaslima.R
import io.github.messiaslima.core.ui.theme.MagentaChatTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ChatViewKtTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mockCallback = mockk<() -> Unit>()

    @Before
    fun setup() {
        every { mockCallback.invoke() } returns Unit
        composeRule.activity.setContent {
            MagentaChatTheme {
                ChatView(onNavigationIconClicked = mockCallback)
            }
        }
    }

    @Test
    fun shouldNavigateBack() {
        composeRule.onNodeWithContentDescription("navigation icon").performClick()

        verify { mockCallback.invoke() }
    }

    @Test
    fun shouldSendMessageAsMainUser() {
        val message = "message as main user"

        composeRule.onNodeWithContentDescription("message text field")
            .performClick()
            .performTextInput(message)

        composeRule.onNodeWithContentDescription("send message")
            .performClick()

        composeRule.onNodeWithText(message)
            .assertExists(errorMessageOnFail = "There is no message with content: $message" )
    }

    @Test
    fun shouldSendMessageAsOtherUser() {
        val message = "message as other user"

        composeRule.onNodeWithContentDescription("menu icon")
            .performClick()

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.user_indicator))
            .assertIsDisplayed()

        composeRule.onNodeWithContentDescription("message text field")
            .performClick()
            .performTextInput(message)

        composeRule.onNodeWithContentDescription("send message")
            .performClick()

        composeRule.onNodeWithText(message)
            .assertExists(errorMessageOnFail = "There is no message with content: $message" )
    }
}