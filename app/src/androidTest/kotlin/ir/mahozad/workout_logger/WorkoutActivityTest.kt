package ir.mahozad.workout_logger

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<UsersActivity>()

    @Test fun theWorkoutImageShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen()
            }
        }
        composeTestRule.onNodeWithTag("image").assertIsDisplayed()
    }

    @Test fun theWorkoutImageAttributionShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen()
            }
        }
        composeTestRule.onNodeWithTag("image-attribution").assertIsDisplayed()
    }


    @Test fun theWorkoutDescriptionShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen()
            }
        }
        composeTestRule.onNodeWithTag("description").assertIsDisplayed()
    }
}
