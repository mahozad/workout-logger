package ir.mahozad.workout_logger.ui.workouts

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import ir.mahozad.workout_logger.data.entity.Workout
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutsReportActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<WorkoutsReportActivity>()
    val viewModel = mockk<WorkoutsReportViewModel>()

    @Test fun usersShouldBeDisplayed() {
        val workout1 = Workout(1, 24, 19)
        val workout2 = Workout(2, 21, 13)
        every { viewModel.getAllWorkouts() } returns flowOf(listOf(workout1, workout2))
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
        val workouts = listOf(
            Workout(1, 24, 19),
            Workout(2, 21, 13)
        )
        every { viewModel.getAllWorkouts() } returns flowOf(workouts)
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutsScreen(viewModel)
            }
        }
        composeTestRule.onNodeWithTag("workouts").assert(hasScrollAction())
    }
}
