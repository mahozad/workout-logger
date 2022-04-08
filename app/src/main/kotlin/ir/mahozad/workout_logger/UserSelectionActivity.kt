package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
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
    var selected by rememberSaveable { mutableStateOf(-1) }
    Column {
        Column(Modifier
                   .testTag("users")
                   .weight(1f, fill = true)
        ) {
            users.forEach { user ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selected == user.id,
                        onClick = { selected = user.id },
                        modifier = Modifier.testTag("radio-${user.id}")
                    )
                    Text(user.firstName)
                }
            }
        }
        Button(onClick = {}, modifier = Modifier.testTag("button"), enabled = selected >= 0) {
            Text(stringResource(R.string.start_workout))
        }
    }
}
