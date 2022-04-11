package ir.mahozad.workout_logger.workout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.workout_logger.R
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.user.add.Input

@AndroidEntryPoint
class WorkoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    WorkoutScreen()
                }
            }
        }
    }
}

@Composable
fun WorkoutScreen(viewModel: WorkoutViewModel = viewModel()) {
    val uriHandler = LocalUriHandler.current
    var total by rememberSaveable { mutableStateOf(0) }
    var correct by rememberSaveable { mutableStateOf(0) }
    Column(
        Modifier
            .testTag("screen")
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painterResource(R.drawable.pushup),
                contentDescription = stringResource(R.string.workout_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().testTag("image")
            )
            val annotatedString = buildAnnotatedString {
                append("Workout vectors by ")
                pushStringAnnotation(
                    tag = "link",
                    annotation = "https://www.vecteezy.com/free-vector/workout"
                )
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Vecteezy")
                }
                pop()
            }
            ClickableText(
                annotatedString,
                modifier = Modifier.testTag("image-attribution").align(Alignment.TopStart),
                onClick = { offset ->
                    annotatedString
                        .getStringAnnotations(tag = "link", start = offset, end = offset)
                        .firstOrNull()
                        ?.let { uriHandler.openUri(it.item) }
                }
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            stringResource(R.string.pushup_description),
            modifier = Modifier.testTag("description")
        )
        Spacer(Modifier.height(8.dp))
        Input(
            shouldRequestFocus = false,
            label = stringResource(R.string.total_pushups),
            placeholder = stringResource(R.string.total_pushups_placeholder),
            keyboardType = KeyboardType.Number,
            tag = "input-total",
            onTextChange = { total = it.toInt() }
        )
        Input(
            shouldRequestFocus = false,
            label = stringResource(R.string.correct_pushups),
            placeholder = stringResource(R.string.correct_pushups_placeholder),
            keyboardType = KeyboardType.Number,
            tag = "input-correct",
            onTextChange = { correct = it.toInt() }
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            viewModel.addWorkout(total, correct)
        }, modifier = Modifier.testTag("finish").align(Alignment.CenterHorizontally), content = {
            Text(stringResource(R.string.finish))
        })
    }
}
