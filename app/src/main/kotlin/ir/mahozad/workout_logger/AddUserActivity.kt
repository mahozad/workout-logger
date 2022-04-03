package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
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
}

@Composable
fun AddUserScreen() {
    var firstName by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    Column {
        Text(stringResource(R.string.user_information))
        Text(stringResource(R.string.user_name))
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(stringResource(R.string.fist_name_label)) },
            modifier = Modifier
                .focusRequester(focusRequester)
                .testTag("input-first-name")
        )
    }
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}
