package ir.mahozad.workout_logger.ui.userSelect

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.workout_logger.R
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.ui.workout.WorkoutActivity

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
    val context = LocalContext.current
    val users by viewModel.getAllUsers().collectAsState(emptyList())
    var selected by rememberSaveable { mutableStateOf(-1) }
    Column {
        Column(
            Modifier
                .testTag("users")
                .weight(1f, fill = true)
                .verticalScroll(rememberScrollState())
        ) {
            users.forEach { user ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selected == user.id,
                        onClick = { selected = user.id },
                        modifier = Modifier.testTag("radio-${user.id}")
                    )
                    Text(user.firstName)
                    Spacer(Modifier.width(8.dp))
                    Text(user.lastName)
                }
            }
        }
        Button(onClick = {
            context.startActivity(Intent(context, WorkoutActivity::class.java))
        }, modifier = Modifier.testTag("button"), enabled = selected >= 0) {
            Text(stringResource(R.string.start_workout))
        }
    }
}
