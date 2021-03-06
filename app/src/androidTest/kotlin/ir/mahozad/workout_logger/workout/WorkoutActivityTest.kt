package ir.mahozad.workout_logger.workout

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import ir.mahozad.workout_logger.R
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.workouts
import kotlinx.coroutines.flow.MutableStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutActivityTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<WorkoutActivity>()
    val viewModel = mockk<WorkoutViewModel>(relaxed = true)

    @Test fun theWorkoutImageShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        composeTestRule.onNodeWithTag("image").assertIsDisplayed()
    }

    @Test fun theWorkoutImageAttributionShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        composeTestRule.onNodeWithTag("image-attribution").assertIsDisplayed()
    }

    @Test fun theWorkoutDescriptionShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        composeTestRule.onNodeWithTag("description").assertIsDisplayed()
    }

    @Test fun theFinishButtonShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        composeTestRule.onNodeWithTag("finish").assertIsDisplayed()
    }

    @Test fun theLabelForTotalInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        val text = composeTestRule.activity.getString(R.string.total_pushups)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theTotalInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        composeTestRule.onNodeWithTag("input-total").assertIsDisplayed()
    }

    @Test fun theTotalInputShouldInitiallyBeEmptyAndHaveProperPlaceholder() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        val text = composeTestRule.activity.getString(R.string.total_pushups_placeholder)
        composeTestRule.onNodeWithTag("input-total").assertTextContains(text)
    }

    @Test fun theLabelForCorrectRepetitionsInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        val text = composeTestRule.activity.getString(R.string.correct_pushups)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theCorrectRepetitionsInputShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        composeTestRule.onNodeWithTag("input-correct").assertIsDisplayed()
    }

    @Test fun theCorrectRepetitionsInputShouldInitiallyBeEmptyAndHaveProperPlaceholder() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        val text = composeTestRule.activity.getString(R.string.correct_pushups_placeholder)
        composeTestRule.onNodeWithTag("input-correct").assertTextContains(text)
    }

    @Test fun whenTheInputsAreValidClickingOnTheFinishButtonShouldCallViewModelWithCorrectValues() {
        every { viewModel.shouldFinish } returns MutableStateFlow(Result.Success)
        // composeTestRule.activity.intent.putExtra("userId", 1)
        // See https://stackoverflow.com/q/68267861 if we want to start activity with extras
        val userId = 1
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId, viewModel)
            }
        }
        composeTestRule.onNodeWithTag("input-total").performTextInput(workouts[0].total.toString())
        composeTestRule.onNodeWithTag("input-correct").performTextInput(workouts[0].correct.toString())
        composeTestRule.onNodeWithTag("finish").performClick()
        verify(exactly = 1) { viewModel.addWorkout(workouts[0].total, workouts[0].correct, userId) }
    }

    @Test fun theWorkoutScreenShouldBeScrollable() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0)
            }
        }
        composeTestRule.onNodeWithTag("screen").assert(hasScrollAction())
    }

    // @Test fun whenInputsAreValidClickingOnTheFinishButtonShouldCallTheOnFinishCallBack_ie_CloseTheActivity() {
    //     val flow: MutableStateFlow<Result> = MutableStateFlow(Result.Ongoing)
    //     every { viewModel.shouldFinish } returns flow
    //     composeTestRule.setContent {
    //         WorkoutLoggerTheme {
    //             WorkoutScreen(viewModel) {
    //                 composeTestRule.activity.finish()
    //             }
    //         }
    //     }
    //     composeTestRule.onNodeWithTag("input-total").performTextInput("23")
    //     composeTestRule.onNodeWithTag("input-correct").performTextInput("19")
    //     composeTestRule.onNodeWithTag("finish").performClick()
    //     flow.value = Result.Success
    //     composeTestRule.mainClock.advanceTimeBy(100)
    //     assertThat(composeTestRule.activity.isFinishing).isTrue()
    // }
    @Test fun whenInputsAreValidClickingOnTheFinishButtonShouldCallTheOnFinishCallBack_ie_CloseTheActivity() {
        val flow: MutableStateFlow<Result> = MutableStateFlow(Result.Ongoing)
        every { viewModel.shouldFinish } returns flow
        var isInvoked = false
        val callback = { isInvoked = true }
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                WorkoutScreen(userId = 0, viewModel, callback)
            }
        }
        composeTestRule.onNodeWithTag("input-total").performTextInput(workouts[0].total.toString())
        composeTestRule.onNodeWithTag("input-correct").performTextInput(workouts[0].correct.toString())
        composeTestRule.onNodeWithTag("finish").performClick()
        flow.value = Result.Success
        composeTestRule.waitForIdle()
        assertThat(isInvoked).isTrue()
    }
}
