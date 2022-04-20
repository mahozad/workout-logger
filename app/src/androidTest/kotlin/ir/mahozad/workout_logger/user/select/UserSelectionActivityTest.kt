package ir.mahozad.workout_logger.user.select

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.users
import ir.mahozad.workout_logger.usersWithId
import ir.mahozad.workout_logger.workout.WorkoutActivity
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserSelectionActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<UserSelectionActivity>()
    val viewModel = mockk<UserSelectionViewModel>()

    @Test fun usersShouldBeDisplayed() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users")
            .assertIsDisplayed()
            .onChildren()
            .assertAny(hasText(users[0].firstName))
            .assertAny(hasText(users[1].firstName))
    }

    @Test fun initiallyTheButtonForStartingWorkoutShouldBeDisabled() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("button").assertIsNotEnabled()
    }

    @Test fun initiallyNoUserShouldBeSelected() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("radio-1").assertIsNotSelected()
        composeTestRule.onNodeWithTag("radio-2").assertIsNotSelected()
    }

    @Test fun clickingOnARadioButtonShouldSelectIt() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("radio-1").performClick()
        composeTestRule.onNodeWithTag("radio-1").assertIsSelected()
    }

    @Test fun afterARadioButtonIsSelectedTheButtonForStartingWorkoutShouldBeEnabled() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("radio-1").performClick()
        composeTestRule.onNodeWithTag("button").assertIsEnabled()
    }

    @Test fun onlyASingleItemShouldBeSelectedAtATime() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
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

    @Test fun ensureTheButtonForStartingWorkoutIsVisible() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("button").assertIsDisplayed()
    }

    @Test fun theUsersListShouldBeScrollable() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users").assert(hasScrollAction())
    }

    @Test fun afterAUserWasSelectedClickingOnTheButtonForStartingWorkoutShouldStartWorkoutActivityWithSelectedUserIdAsData() {
        Intents.init()
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        try {
            composeTestRule.onNodeWithTag("radio-1").performClick()
            composeTestRule.onNodeWithTag("button").performClick()
            Intents.intended(hasComponent(WorkoutActivity::class.java.name))
            Intents.intended(hasExtra("userId", 1))
        } finally {
            Intents.release()
        }
    }

    @Test fun thereShouldBeProperNumberOfDividersBetweenUsers_OneLessThanTotalNumberOfUsers() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }
}
