package ir.mahozad.workout_logger.ui.users

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.data.entity.User
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<UsersActivity>()
    val viewModel = mockk<UsersViewModel>()

    @Test fun usersShouldBeDisplayed() {
        val user1 = User(1, "John", "Smith", "Man", "24")
        val user2 = User(2, "Jane", "Smith", "Woman", "25")
        every { viewModel.getAllUsers() } returns flowOf(listOf(user1, user2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UsersScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users")
            .assertIsDisplayed()
            .onChildren()
            .assertAny(hasText("John"))
            .assertAny(hasText("Jane"))
    }

    @Test fun theUsersListShouldBeScrollable() {
        val users = listOf(
            User(1, "John", "Smith", "Man", "24"),
            User(2, "Jane", "Smith", "Woman", "25")
        )
        every { viewModel.getAllUsers() } returns flowOf(users)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UsersScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users").assert(hasScrollAction())
    }

    @Test fun thereShouldBeProperNumberOfDividersBetweenUsers_OneLessThanTotalNumberOfUsers() {
        val users = listOf(
            User(1, "John", "Smith", "Man", "24"),
            User(2, "Jane", "Smith", "Woman", "25")
        )
        every { viewModel.getAllUsers() } returns flowOf(users)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UsersScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }
}
