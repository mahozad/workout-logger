package ir.mahozad.workout_logger.workout.report

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.usersWithId
import ir.mahozad.workout_logger.workouts
import ir.mahozad.workout_logger.workoutsWithId
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutsReportActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<WorkoutsReportActivity>()
    val viewModel = mockk<WorkoutsReportViewModel>()

    @Test fun workoutsShouldBeDisplayed() {
        every { viewModel.getAllWorkouts() } returns flowOf(mapOf(usersWithId[0] to workoutsWithId.take(2)))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("workouts")
            .assertIsDisplayed()
            .onChildren()
            .assertAny(hasText(workouts[0].total.toString()))
            .assertAny(hasText(workouts[1].total.toString()))
    }

    @Test fun theWorkoutsListShouldBeScrollable() {
        every { viewModel.getAllWorkouts() } returns flowOf(mapOf(usersWithId[0] to workoutsWithId.take(2)))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("workouts").assert(hasScrollAction())
    }

    @Test fun thereShouldBeProperNumberOfDividersBetweenReports_OneLessThanTotalNumberOfReports() {
        every { viewModel.getAllWorkouts() } returns flowOf(mapOf(usersWithId[0] to workoutsWithId.take(2)))
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }

    @Test fun whenThereIsTwoWorkoutsWithDifferentUsersTheyShouldBeDisplayedCorrectly() {
        val workout1 = workoutsWithId[0].copy(userId = usersWithId[0].id)
        val workout2 = workoutsWithId[1].copy(userId = usersWithId[1].id)
        every { viewModel.getAllWorkouts() } returns flowOf(
            mapOf(usersWithId[0] to listOf(workout1), usersWithId[1] to listOf(workout2))
        )
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onAllNodesWithTag("divider").assertCountEquals(1)
    }
}
