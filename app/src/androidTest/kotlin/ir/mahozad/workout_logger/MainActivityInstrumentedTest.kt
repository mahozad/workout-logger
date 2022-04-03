package ir.mahozad.workout_logger

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test fun theButtonForStartingAWorkoutSessionShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.new_workout)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theButtonForCreatingANewUserShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.new_user)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun clickingOnTheNewWorkoutSessionButtonShouldStartTheUserSelectionActivity() {
        Intents.init()
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.new_workout)
        composeTestRule.onNodeWithText(text).performClick()
        try {
            intended(hasComponent(UserSelectionActivity::class.java.name))
        } finally {
            Intents.release()
        }
    }
}
