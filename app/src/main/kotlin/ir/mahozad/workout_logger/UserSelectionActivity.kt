package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme

class UserSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    UserSelectionScreen()
                }
            }
        }
    }

    @Composable
    fun UserSelectionScreen() {
        Button(onClick = {}) {
            Text(stringResource(R.string.start_workout))
        }
    }
}
