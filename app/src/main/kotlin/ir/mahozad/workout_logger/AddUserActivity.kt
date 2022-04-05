package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.mahozad.AddUserViewModel
import ir.mahozad.User
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
fun AddUserScreen(viewModel: AddUserViewModel = viewModel()) {
    var isSuccessPromptVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    Column {
        Text(stringResource(R.string.user_information))
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = true,
            stringResource(R.string.user_first_name),
            stringResource(R.string.user_first_name_label),
            tag = "input-first-name",
            onTextChange = { firstName = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = false,
            stringResource(R.string.user_last_name),
            stringResource(R.string.user_last_name_label),
            tag = "input-last-name",
            onTextChange = { lastName = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = false,
            stringResource(R.string.user_age),
            stringResource(R.string.user_age_label),
            tag = "input-age",
            keyboardType = KeyboardType.Number,
            onTextChange = { age = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Input(
            shouldRequestFocus = false,
            stringResource(R.string.user_sex),
            stringResource(R.string.user_sex_label),
            tag = "input-gender",
            onTextChange = { gender = it }
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        val wasSuccessful = viewModel.addUser(User(firstName, lastName, gender, age))
                        if (wasSuccessful) isSuccessPromptVisible = true
                    }
                },
                modifier = Modifier.testTag("button-create-user")
            ) {
                Text(stringResource(R.string.create_user))
            }
            if (isSuccessPromptVisible) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    stringResource(R.string.user_create_success_message),
                    Modifier.testTag("success-prompt")
                )
                LaunchedEffect(key1 = Unit) {
                    delay(2000)
                    isSuccessPromptVisible = false
                }
            }
        }
    }
}

@Composable
fun Input(
    shouldRequestFocus: Boolean,
    text: String,
    label: String,
    tag: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit
) {
    var value by rememberSaveable { mutableStateOf("") }
    val focusRequester = FocusRequester()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text)
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            value = value,
            onValueChange = {
                value = it
                onTextChange(it)
            },
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
    Input(true, "Name:", "Anna", "", onTextChange = {})
}
