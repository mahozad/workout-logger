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

    @Test fun initiallyTheButtonForStartingWorkoutShouldBeDisabled() {
        val user1 = User(1, "John", "Smith", "Man", "24")
        val user2 = User(2, "Jane", "Smith", "Woman", "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("button").assertIsNotEnabled()
    }

    @Test fun initiallyNoUserShouldBeSelected() {
        val user1 = User(1, "John", "Smith", "Man", "24")
        val user2 = User(2, "Jane", "Smith", "Woman", "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("radio-1").assertIsNotSelected()
        composeTestRule.onNodeWithTag("radio-2").assertIsNotSelected()
    }

    @Test fun clickingOnARadioButtonShouldSelectIt() {
        val user1 = User(1, "John", "Smith", "Man", "24")
        val user2 = User(2, "Jane", "Smith", "Woman", "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("radio-1").performClick()
        composeTestRule.onNodeWithTag("radio-1").assertIsSelected()
    }

    @Test fun afterARadioButtonIsSelectedTheButtonForStartingWorkoutShouldBeEnabled() {
        val user1 = User(1, "John", "Smith", "Man", "24")
        val user2 = User(2, "Jane", "Smith", "Woman", "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("radio-1").performClick()
        composeTestRule.onNodeWithTag("button").assertIsEnabled()
    }

    @Test fun onlyASingleItemShouldBeSelectedAtATime() {
        val user1 = User(1, "John", "Smith", "Man", "24")
        val user2 = User(2, "Jane", "Smith", "Woman", "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("radio-1").performClick()
        composeTestRule.onNodeWithTag("radio-2").assertIsNotSelected()
        composeTestRule.onNodeWithTag("radio-2").performClick()
        composeTestRule.onNodeWithTag("radio-1").assertIsNotSelected()
    }
}
