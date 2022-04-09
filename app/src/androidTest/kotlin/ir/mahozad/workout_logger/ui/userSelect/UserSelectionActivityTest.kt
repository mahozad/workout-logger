package ir.mahozad.workout_logger.ui.userSelect

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.data.entity.User
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.ui.workout.WorkoutActivity
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

    @Test fun ensureTheButtonForStartingWorkoutIsVisible() {
        val users = listOf(
            User(1, "John", "Smith", "Man", "24"),
            User(2, "Jane", "Smith", "Woman", "25"),
            User(3, "Anne", "Smith", "Woman", "26"),
            User(4, "Anna", "Smith", "Woman", "27"),
            User(5, "Lily", "Smith", "Woman", "28"),
            User(6, "Ella", "Smith", "Woman", "29"),
            User(7, "Joan", "Smith", "Woman", "30"),
            User(8, "Lisa", "Smith", "Woman", "31"),
            User(9, "Mary", "Smith", "Woman", "32"),
            User(10, "Rose", "Smith", "Woman", "33"),
            User(11, "Alan", "Smith", "Man", "34"),
            User(12, "Carl", "Smith", "Man", "35"),
            User(13, "Eric", "Smith", "Man", "36"),
            User(14, "Evan", "Smith", "Man", "37"),
            User(15, "Luke", "Smith", "Man", "38"),
            User(16, "Matt", "Smith", "Man", "39"),
            User(17, "Neil", "Smith", "Man", "40"),
            User(18, "Sean", "Smith", "Man", "41"),
            User(19, "Paul", "Smith", "Man", "42"),
            User(20, "Ryan", "Smith", "Man", "43")
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
            User(1, "John", "Smith", "Man", "24"),
            User(2, "Jane", "Smith", "Woman", "25")
        )
        every { viewModel.getAllUsers() } returns flowOf(users)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UserSelectionScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users").assert(hasScrollAction())
    }

    @Test
    fun afterAUserWasSelectedClickingOnTheButtonForStartingWorkoutShouldStartWorkoutActivity() {
        Intents.init()
        val users = listOf(
            User(1, "John", "Smith", "Man", "24"),
            User(2, "Jane", "Smith", "Woman", "25")
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
        } finally {
            Intents.release()
        }
    }
}
