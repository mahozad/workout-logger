package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
}

@Composable
fun AddUserScreen() {
    var firstName by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    Column {
        Text(stringResource(R.string.user_information))
        Spacer(modifier = Modifier.height(10.dp))
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.user_name))
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(stringResource(R.string.fist_name_label)) },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .testTag("input-first-name")
            )
        }
    }
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}
