package io.github.messiaslima.feature.chat.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.messiaslima.MainActivity
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
            ChatView(onNavigationIconClicked = mockCallback)
        }
    }

    @Test
    fun shouldNavigateBack() {
        composeRule.onNodeWithContentDescription("navigation icon").performClick()

        verify { mockCallback.invoke() }
    }
}