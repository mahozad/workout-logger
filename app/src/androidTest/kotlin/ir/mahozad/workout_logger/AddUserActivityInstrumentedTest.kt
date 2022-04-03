package ir.mahozad.workout_logger

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddUserActivityInstrumentedTest {
    @get:Rule val composeTestRule = createAndroidComposeRule<AddUserActivity>()

    @Test fun theHeaderTextShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_information)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theLabelForFirstNameInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_name)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theFirstNameInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-first-name").assertIsDisplayed()
    }

    @Test fun theFirstNameInputShouldHaveFocus() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-first-name").assertIsFocused()
    }

    @Test fun theFirstNameInputShouldInitiallyBeEmptyAndHaveTheProperLabel() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.fist_name_label)
        composeTestRule.onNodeWithTag("input-first-name").assertTextContains(text)
    }
}
