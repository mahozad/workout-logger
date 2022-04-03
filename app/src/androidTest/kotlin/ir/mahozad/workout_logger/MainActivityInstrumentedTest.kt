package ir.mahozad.workout_logger

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test fun theButtonForStartingAWorkoutSessionShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen("New workout")
            }
        }
        composeTestRule.onNodeWithText("New workout").assertIsDisplayed()
    }
}
