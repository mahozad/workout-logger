package ir.mahozad.workout_logger.data

import androidx.annotation.StringRes
import ir.mahozad.workout_logger.R

enum class Sex(@StringRes val displayStringRes: Int) {
    MALE(R.string.sex_male),
    FEMALE(R.string.sex_female),
    OTHER(R.string.sex_other)
}
