package ir.mahozad.workout_logger.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.mahozad.workout_logger.R
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme
import ir.mahozad.workout_logger.user.add.AddUserActivity
import ir.mahozad.workout_logger.user.list.UsersActivity
import ir.mahozad.workout_logger.user.select.UserSelectionActivity
import ir.mahozad.workout_logger.workout.report.WorkoutsReportActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val buttonWidth = rememberSaveable { 124 }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.width(buttonWidth.dp),
            onClick = { context.startActivity(Intent(context, UserSelectionActivity::class.java)) }
        ) {
            Text(stringResource(R.string.new_workout))
        }
        Button(
            modifier = Modifier.width(buttonWidth.dp),
            onClick = { context.startActivity(Intent(context, AddUserActivity::class.java)) }
        ) {
            Text(stringResource(R.string.new_user))
        }
        Button(
            modifier = Modifier.width(buttonWidth.dp),
            onClick = { context.startActivity(Intent(context, UsersActivity::class.java)) }
        ) {
            Text(stringResource(R.string.all_users))
        }
        Button(
            modifier = Modifier.width(buttonWidth.dp),
            onClick = { context.startActivity(Intent(context, WorkoutsReportActivity::class.java)) }
        ) {
            Text(stringResource(R.string.workouts_report))
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
