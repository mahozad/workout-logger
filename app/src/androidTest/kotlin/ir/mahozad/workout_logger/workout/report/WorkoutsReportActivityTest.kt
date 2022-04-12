package ir.mahozad.workout_logger.workout.report

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.data.Sex
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.data.Workout
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutsReportActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<WorkoutsReportActivity>()
    val viewModel = mockk<WorkoutsReportViewModel>()

    @Test fun workoutsShouldBeDisplayed() {
        val user = User(1, "John", "Smith", Sex.MALE, "24")
        val workouts = listOf(
            Workout(1, 24, 19, user.id),
            Workout(2, 21, 13, user.id)
        )
        every { viewModel.getAllWorkouts() } returns flowOf(mapOf(user to workouts))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("workouts")
            .assertIsDisplayed()
            .onChildren()
            .assertAny(hasText("24"))
            .assertAny(hasText("21"))
    }

    @Test fun theWorkoutsListShouldBeScrollable() {
        val user = User(1, "John", "Smith", Sex.MALE, "24")
        val workouts = listOf(
            Workout(1, 24, 19, user.id),
            Workout(2, 21, 13, user.id)
        )
        every { viewModel.getAllWorkouts() } returns flowOf(mapOf(user to workouts))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("workouts").assert(hasScrollAction())
    }

    @Test fun thereShouldBeProperNumberOfDividersBetweenReports_OneLessThanTotalNumberOfReports() {
        val user = User(1, "John", "Smith", Sex.MALE, "24")
        val workouts = listOf(
            Workout(1, 24, 19, user.id),
            Workout(2, 20, 13, user.id)
        )
        every { viewModel.getAllWorkouts() } returns flowOf(mapOf(user to workouts))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }

    @Test fun whenThereIsTwoWorkoutsWithDifferentUsersTheyShouldBeDisplayedCorrectly() {
        val user1 = User(1, "John", "Smith", Sex.MALE, "24")
        val user2 = User(2, "Jane", "Smith", Sex.FEMALE, "25")
        val workout1 = Workout(1, 24, 19, user1.id)
        val workout2 = Workout(2, 20, 13, user2.id)
        every { viewModel.getAllWorkouts() } returns flowOf(
            mapOf(user1 to listOf(workout1), user2 to listOf(workout2))
        )
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }
}
