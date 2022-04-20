package ir.mahozad.workout_logger.user.list

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.users
import ir.mahozad.workout_logger.usersWithId
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<UsersActivity>()
    val viewModel = mockk<UsersViewModel>()

    @Test fun usersShouldBeDisplayed() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UsersScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users")
            .assertIsDisplayed()
            .onChildren()
            .assertAny(hasText(users[0].firstName))
            .assertAny(hasText(users[1].firstName))
    }

    @Test fun theUsersListShouldBeScrollable() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UsersScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("users").assert(hasScrollAction())
    }

    @Test fun thereShouldBeProperNumberOfDividersBetweenUsers_OneLessThanTotalNumberOfUsers() {
        every { viewModel.getAllUsers() } returns flowOf(usersWithId.take(2))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                UsersScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }
}
