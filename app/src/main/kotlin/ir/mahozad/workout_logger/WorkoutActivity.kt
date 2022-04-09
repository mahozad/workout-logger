package ir.mahozad.workout_logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme

@AndroidEntryPoint
class WorkoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    WorkoutScreen()
                }
            }
        }
    }
}

@Composable
fun WorkoutScreen() {
    val uriHandler = LocalUriHandler.current
    Column {
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
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append("Vecteezy")
            }
            pop()
        }
        ClickableText(
            annotatedString,
            modifier = Modifier.testTag("image-attribution"),
            onClick = { offset ->
                annotatedString
                    .getStringAnnotations(tag = "link", start = offset, end = offset)
                    .firstOrNull()
                    ?.let { uriHandler.openUri(it.item) }
            }
        )
        Text(
            stringResource(R.string.pushup_description),
            modifier = Modifier.testTag("description")
        )

        Button(onClick = {}, modifier = Modifier.testTag("finish"), content = {
            Text(stringResource(R.string.finish))
        })
    }
}
