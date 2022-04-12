package ir.mahozad.workout_logger.user.select

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.data.Sex
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
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
        val user1 = User(1, "John", "Smith", Sex.MALE, "24")
        val user2 = User(2, "Jane", "Smith", Sex.FEMALE, "25")
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
        val user1 = User(1, "John", "Smith", Sex.MALE, "24")
        val user2 = User(2, "Jane", "Smith", Sex.FEMALE, "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("button").assertIsNotEnabled()
    }

    @Test fun initiallyNoUserShouldBeSelected() {
        val user1 = User(1, "John", "Smith", Sex.MALE, "24")
        val user2 = User(2, "Jane", "Smith", Sex.FEMALE, "25")
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
        val user1 = User(1, "John", "Smith", Sex.MALE, "24")
        val user2 = User(2, "Jane", "Smith", Sex.FEMALE, "25")
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
        val user1 = User(1, "John", "Smith", Sex.MALE, "24")
        val user2 = User(2, "Jane", "Smith", Sex.FEMALE, "25")
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
        val user1 = User(1, "John", "Smith", Sex.MALE, "24")
        val user2 = User(2, "Jane", "Smith", Sex.FEMALE, "25")
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

    @Test fun ensureTheButtonForStartingWorkoutIsVisible() {
        val users = listOf(
            User(1, "John", "Smith", Sex.MALE, "24"),
            User(2, "Jane", "Smith", Sex.FEMALE, "25"),
            User(3, "Anne", "Smith", Sex.FEMALE, "26"),
            User(4, "Anna", "Smith", Sex.FEMALE, "27"),
            User(5, "Lily", "Smith", Sex.FEMALE, "28"),
            User(6, "Ella", "Smith", Sex.FEMALE, "29"),
            User(7, "Joan", "Smith", Sex.FEMALE, "30"),
            User(8, "Lisa", "Smith", Sex.FEMALE, "31"),
            User(9, "Mary", "Smith", Sex.FEMALE, "32"),
            User(10, "Rose", "Smith", Sex.FEMALE, "33"),
            User(11, "Alan", "Smith", Sex.MALE, "34"),
            User(12, "Carl", "Smith", Sex.MALE, "35"),
            User(13, "Eric", "Smith", Sex.MALE, "36"),
            User(14, "Evan", "Smith", Sex.MALE, "37"),
            User(15, "Luke", "Smith", Sex.MALE, "38"),
            User(16, "Matt", "Smith", Sex.MALE, "39"),
            User(17, "Neil", "Smith", Sex.MALE, "40"),
            User(18, "Sean", "Smith", Sex.MALE, "41"),
            User(19, "Paul", "Smith", Sex.MALE, "42"),
            User(20, "Ryan", "Smith", Sex.MALE, "43")
        )
        every { viewModel.getAllUsers() } returns flowOf(users)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("button").assertIsDisplayed()
    }

    @Test fun theUsersListShouldBeScrollable() {
        val users = listOf(
            User(1, "John", "Smith", Sex.MALE, "24"),
            User(2, "Jane", "Smith", Sex.FEMALE, "25")
        )
        every { viewModel.getAllUsers() } returns flowOf(users)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users").assert(hasScrollAction())
    }

    @Test fun afterAUserWasSelectedClickingOnTheButtonForStartingWorkoutShouldStartWorkoutActivityWithSelectedUserIdAsData() {
        Intents.init()
        val users = listOf(
            User(1, "John", "Smith", Sex.MALE, "24"),
            User(2, "Jane", "Smith", Sex.FEMALE, "25")
        )
        every { viewModel.getAllUsers() } returns flowOf(users)
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
        val users = listOf(
            User(1, "John", "Smith", Sex.MALE, "24"),
            User(2, "Jane", "Smith", Sex.FEMALE, "25")
        )
        every { viewModel.getAllUsers() } returns flowOf(users)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }
}
