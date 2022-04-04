package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
    Column {
        Text(stringResource(R.string.user_information))
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = true,
            stringResource(R.string.user_fist_name),
            stringResource(R.string.user_fist_name_label),
            tag = "input-first-name"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = false,
            stringResource(R.string.user_last_name),
            stringResource(R.string.user_last_name_label),
            tag = "input-last-name"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = false,
            stringResource(R.string.user_age),
            stringResource(R.string.user_age_label),
            tag = "input-age",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = false,
            stringResource(R.string.user_sex),
            stringResource(R.string.user_sex_label),
            tag = "input-gender"
        )
    }
}

@Composable
fun Input(
    shouldRequestFocus: Boolean,
    text: String,
    label: String,
    tag: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var value by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text)
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            value = value,
            onValueChange = { value = it },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .focusRequester(focusRequester)
                .testTag(tag)
        )
    }

    if (shouldRequestFocus) {
        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { }
        }
    }
}

@Preview(showBackground = true, widthDp = 350, heightDp = 100)
@Composable
fun InputPreview() {
    Input(true, "Name:", "Anna", "")
}
