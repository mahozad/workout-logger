package ir.mahozad.workout_logger

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.User
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserSelectionActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<UserSelectionActivity>()
    val viewModel = mockk<UserSelectionViewModel>()

    @Test fun usersShouldBeDisplayed() {
        val user1 = User(1, "John", "Smith", "Man", "24")
        val user2 = User(2, "Jane", "Smith", "Woman", "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users")
            .assertIsDisplayed()
            .onChildren()
            .assertAny(hasText("John"))
            .assertAny(hasText("Jane"))
    }
}
