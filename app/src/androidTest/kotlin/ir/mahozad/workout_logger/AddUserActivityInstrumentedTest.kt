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
        val text = composeTestRule.activity.getString(R.string.user_fist_name)
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
        val text = composeTestRule.activity.getString(R.string.user_fist_name_label)
        composeTestRule.onNodeWithTag("input-first-name").assertTextContains(text)
    }

    @Test fun theLabelForLastNameInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_last_name)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theLastNameInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-last-name").assertIsDisplayed()
    }

    @Test fun theLastNameInputShouldInitiallyBeEmptyAndHaveTheProperLabel() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_last_name_label)
        composeTestRule.onNodeWithTag("input-last-name").assertTextContains(text)
    }

    @Test fun theLabelForAgeInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_age)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theAgeInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-age").assertIsDisplayed()
    }

    @Test fun theAgeInputShouldInitiallyBeEmptyAndHaveTheProperLabel() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_age_label)
        composeTestRule.onNodeWithTag("input-age").assertTextContains(text)
    }

    @Test fun theLabelForGenderInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_sex)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theGenderInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-gender").assertIsDisplayed()
    }

    @Test fun theGenderInputShouldInitiallyBeEmptyAndHaveTheProperLabel() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.user_sex_label)
        composeTestRule.onNodeWithTag("input-gender").assertTextContains(text)
    }

    @Test fun theButtonForCreatingTheUserShouldBeShown() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.create_user)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }
}
