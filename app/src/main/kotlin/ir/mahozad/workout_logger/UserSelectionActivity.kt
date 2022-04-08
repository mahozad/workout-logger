package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme

@AndroidEntryPoint
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
}

@Composable
fun UserSelectionScreen(viewModel: UserSelectionViewModel = viewModel()) {
    val users by viewModel.getAllUsers().collectAsState(emptyList())
    Column(Modifier.testTag("users")) {
        users.forEach { user ->
            Text(user.firstName)
        }
    }
    Button(onClick = {}) {
        Text(stringResource(R.string.start_workout))
    }
}
