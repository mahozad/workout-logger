package ir.mahozad.workout_logger.user.add

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
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
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.workout_logger.R
import ir.mahozad.workout_logger.data.Sex
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import kotlinx.coroutines.delay

@AndroidEntryPoint
class AddUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AddUserScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen(viewModel: AddUserViewModel = viewModel()) {
    var isSuccessPromptVisible by remember { mutableStateOf(false) }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var sex by rememberSaveable { mutableStateOf<Sex?>(null) }
    Column {
        Text(stringResource(R.string.user_information), Modifier.align(Alignment.CenterHorizontally))
        Column(Modifier.weight(1f, fill = true).fillMaxSize().padding(8.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            Input(
                shouldRequestFocus = true,
                stringResource(R.string.user_first_name),
                stringResource(R.string.user_first_name_placeholder),
                tag = "input-first-name",
                onTextChange = { firstName = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Input(
                shouldRequestFocus = false,
                stringResource(R.string.user_last_name),
                stringResource(R.string.user_last_name_placeholder),
                tag = "input-last-name",
                onTextChange = { lastName = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Input(
                shouldRequestFocus = false,
                stringResource(R.string.user_age),
                stringResource(R.string.user_age_placeholder),
                tag = "input-age",
                keyboardType = KeyboardType.Number,
                onTextChange = { age = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.user_sex))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = sex == Sex.MALE,
                        onClick = { sex = Sex.MALE },
                        modifier = Modifier.testTag("sex-1")
                    )
                    Text(stringResource(R.string.sex_male))
                    Spacer(Modifier.width(10.dp))
                    RadioButton(
                        selected = sex == Sex.FEMALE,
                        onClick = { sex = Sex.FEMALE },
                        modifier = Modifier.testTag("sex-2")
                    )
                    Text(stringResource(R.string.sex_female))
                    Spacer(Modifier.width(10.dp))
                    RadioButton(
                        selected = sex == Sex.OTHER,
                        onClick = { sex = Sex.OTHER },
                        modifier = Modifier.testTag("sex-3")
                    )
                    Text(stringResource(R.string.sex_other))
                }
            }
        }
        Button(
            onClick = {
                val wasSuccessful = viewModel.addUser(User(0, firstName, lastName, sex!!, age))
                if (wasSuccessful) isSuccessPromptVisible = true
            },
            modifier = Modifier
                .testTag("button-create-user")
                .align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.create_user))
        }
        if (isSuccessPromptVisible) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                stringResource(R.string.user_create_success_message),
                Modifier
                    .testTag("success-prompt")
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            LaunchedEffect(key1 = Unit) {
                delay(2000)
                isSuccessPromptVisible = false
            }
        }
    }
}

@Composable
fun Input(
    shouldRequestFocus: Boolean,
    label: String,
    placeholder: String,
    tag: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit
) {
    var value by rememberSaveable { mutableStateOf("") }
    val focusRequester = FocusRequester()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            value = value,
            onValueChange = {
                value = it
                onTextChange(it)
            },
            label = { Text(placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
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
