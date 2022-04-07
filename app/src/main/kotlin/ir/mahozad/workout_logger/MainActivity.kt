package ir.mahozad.workout_logger

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    Column {
        Button(onClick = {
            context.startActivity(Intent(context, UserSelectionActivity::class.java))
        }) {
            Text(stringResource(R.string.new_workout))
        }
        Button(onClick = {
            context.startActivity(Intent(context, AddUserActivity::class.java))
        }) {
            Text(stringResource(R.string.new_user))
        }
        Button(onClick = {
            context.startActivity(Intent(context, UsersActivity::class.java))
        }) {
            Text(stringResource(R.string.all_users))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkoutLoggerTheme {
        MainScreen()
    }
}
