package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme

class AddUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AddUserScreen()
                }
            }
        }
    }

    @Composable
    fun AddUserScreen() {
        Text(stringResource(R.string.user_information))
    }
}
