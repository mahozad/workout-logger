package ir.mahozad.workout_logger.ui.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.R
import ir.mahozad.workout_logger.ui.addUser.AddUserActivity
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.ui.userSelect.UserSelectionActivity
import ir.mahozad.workout_logger.ui.users.UsersActivity
import ir.mahozad.workout_logger.ui.workouts.WorkoutsReportActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

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

    @Test fun theButtonForShowingAllUsersShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.all_users)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun theButtonForShowingWorkoutsReportShouldBeDisplayed() {
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        val text = composeTestRule.activity.getString(R.string.workouts_report)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test fun clickingOnTheNewWorkoutSessionButtonShouldStartTheUserSelectionActivity() {
        Intents.init()
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        try {
            val text = composeTestRule.activity.getString(R.string.new_workout)
            composeTestRule.onNodeWithText(text).performClick()
            intended(hasComponent(UserSelectionActivity::class.java.name))
        } finally {
            Intents.release()
        }
    }

    @Test fun clickingOnTheNewUserButtonShouldStartTheAddUserActivity() {
        Intents.init()
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        try {
            val text = composeTestRule.activity.getString(R.string.new_user)
            composeTestRule.onNodeWithText(text).performClick()
            intended(hasComponent(AddUserActivity::class.java.name))
        } finally {
            Intents.release()
        }
    }

    @Test fun clickingOnTheAllUsersButtonShouldStartTheAllUsersActivity() {
        Intents.init()
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        try {
            val text = composeTestRule.activity.getString(R.string.all_users)
            composeTestRule.onNodeWithText(text).performClick()
            intended(hasComponent(UsersActivity::class.java.name))
        } finally {
            Intents.release()
        }
    }

    @Test fun clickingOnTheWorkoutsReportButtonShouldStartTheWorkoutsReportActivity() {
        Intents.init()
        composeTestRule.setContent {
            WorkoutLoggerTheme {
                MainScreen()
            }
        }
        try {
            val text = composeTestRule.activity.getString(R.string.workouts_report)
            composeTestRule.onNodeWithText(text).performClick()
            intended(hasComponent(WorkoutsReportActivity::class.java.name))
        } finally {
            Intents.release()
        }
    }
}
