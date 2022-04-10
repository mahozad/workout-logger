package ir.mahozad.workout_logger.ui.workouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.workout_logger.ui.theme.WorkoutLoggerTheme

@AndroidEntryPoint
class WorkoutsReportActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLoggerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    WorkoutsScreen()
                }
            }
        }
    }
}

@Composable
fun WorkoutsScreen(viewModel: WorkoutsReportViewModel = viewModel()) {
    val workouts by viewModel.getAllWorkouts().collectAsState(emptyList())
    Column(
        Modifier
            .testTag("workouts")
            .verticalScroll(rememberScrollState())
    ) {
        workouts.forEach { workout ->
            Text(workout.total.toString())
        }
    }
}