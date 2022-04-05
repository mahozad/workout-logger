package ir.mahozad.workout_logger

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import ir.mahozad.AddUserViewModel
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddUserActivityInstrumentedTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<AddUserActivity>()
    val viewModel = mockk<AddUserViewModel>()

    // InstrumentationRegistry.getInstrumentation()

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
        val text = composeTestRule.activity.getString(R.string.user_first_name)
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
        val text = composeTestRule.activity.getString(R.string.user_first_name_label)
        composeTestRule.onNodeWithTag("input-first-name").assertTextContains(text)
    }

    @Ignore("The test fails (false positive) but the app works correctly. See https://stackoverflow.com/q/71742914")
    @Test fun whenAConfigChangeHappensTheFirstNameInputShouldRetainItsValue() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-first-name").performTextInput("John")
        composeTestRule.activity.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("input-first-name").assertTextEquals("John")
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

    @Test fun initiallyTheSuccessMessageShouldNotBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("success-prompt").assertDoesNotExist()
    }

    @Test fun whenTheInputsAreValidClickingOnTheButtonForCreatingUserShouldCallViewModelWithCorrectValues() =
        runBlocking {
            // val spyViewModel = spyk<AddUserViewModel>()
            coEvery { viewModel.addUser(any()) } returns true
            // val spyViewModel = mockk<AddUserViewModel>()
            composeTestRule.setContent {
                WorkoutLoggerTheme {
                    AddUserScreen(viewModel)
                }
            }
            composeTestRule.onNodeWithTag("input-first-name").performTextInput("John")
            composeTestRule.onNodeWithTag("input-last-name").performTextInput("Smith")
            composeTestRule.onNodeWithTag("input-gender").performTextInput("Man")
            composeTestRule.onNodeWithTag("input-age").performTextInput("24")
            composeTestRule.onNodeWithTag("button-create-user").performClick()
            composeTestRule.waitForIdle()
            composeTestRule.mainClock.advanceTimeBy(3_000)
            coVerify(exactly = 1) { viewModel.addUser(any()) }
        }

    @Test fun whenTheInputsAreValidClickingOnTheButtonForCreatingUserShouldShowSuccessMessage() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-first-name").performTextInput("John")
        composeTestRule.onNodeWithTag("input-last-name").performTextInput("Smith")
        composeTestRule.onNodeWithTag("input-age").performTextInput("24")
        composeTestRule.onNodeWithTag("input-gender").performTextInput("Man")
        composeTestRule.onNodeWithTag("button-create-user").performClick()
        composeTestRule.onNodeWithTag("success-prompt").assertIsDisplayed()
    }

    @Test fun whenTheInputsAreValidAndTheButtonForCreatingUserIsPressedAndViewModelReturnsErrorFlagShouldNotShowSuccessMessage(): Unit =
        runBlocking {
            coEvery { viewModel.addUser(any()) } returns false

            composeTestRule.setContent {
                WorkoutLoggerTheme {
                    AddUserScreen(viewModel)
                }
            }
            composeTestRule.onNodeWithTag("input-first-name").performTextInput("John")
            composeTestRule.onNodeWithTag("input-last-name").performTextInput("Smith")
            composeTestRule.onNodeWithTag("input-gender").performTextInput("Man")
            composeTestRule.onNodeWithTag("input-age").performTextInput("24")
            composeTestRule.onNodeWithTag("button-create-user").performClick()
            composeTestRule.onNodeWithTag("success-prompt").assertDoesNotExist()
        }

    @Test fun theSuccessMessageShouldDisappearAfterAGivenAmountOfTime() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                AddUserScreen()
            }
        }
        composeTestRule.onNodeWithTag("input-first-name").performTextInput("John")
        composeTestRule.onNodeWithTag("input-last-name").performTextInput("Smith")
        composeTestRule.onNodeWithTag("input-age").performTextInput("24")
        composeTestRule.onNodeWithTag("input-gender").performTextInput("Man")
        composeTestRule.onNodeWithTag("button-create-user").performClick()

        composeTestRule.mainClock.advanceTimeBy(3_000)
        composeTestRule.onNodeWithTag("success-prompt").assertDoesNotExist()
    }
}
