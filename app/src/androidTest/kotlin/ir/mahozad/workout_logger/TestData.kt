package ir.mahozad.workout_logger

import ir.mahozad.workout_logger.data.Sex
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.data.Workout

val users = listOf(
    User(0, "John", "Smith", Sex.MALE, "24"),
    User(0, "Jane", "Smith", Sex.FEMALE, "25"),
    User(0, "Anne", "Smith", Sex.FEMALE, "26"),
    User(0, "Anna", "Smith", Sex.FEMALE, "27"),
    User(0, "Lily", "Smith", Sex.FEMALE, "28"),
    User(0, "Ella", "Smith", Sex.FEMALE, "29"),
    User(0, "Joan", "Smith", Sex.FEMALE, "30"),
    User(0, "Lisa", "Smith", Sex.FEMALE, "31"),
    User(0, "Mary", "Smith", Sex.FEMALE, "32"),
    User(0, "Rose", "Smith", Sex.FEMALE, "33"),
    User(0, "Alan", "Smith", Sex.MALE, "34"),
    User(0, "Carl", "Smith", Sex.MALE, "35"),
    User(0, "Eric", "Smith", Sex.MALE, "36"),
    User(0, "Evan", "Smith", Sex.MALE, "37"),
    User(0, "Luke", "Smith", Sex.MALE, "38"),
    User(0, "Matt", "Smith", Sex.MALE, "39"),
    User(0, "Neil", "Smith", Sex.MALE, "40"),
    User(0, "Sean", "Smith", Sex.MALE, "41"),
    User(0, "Paul", "Smith", Sex.MALE, "42"),
    User(0, "Ryan", "Smith", Sex.MALE, "43")
)

val workouts = listOf(
    Workout(0, 23, 19, 1),
    Workout(0, 20, 13, 1),
    Workout(0, 24, 22, 1),
    Workout(0, 13, 10, 1)
)

val usersWithId = users.mapIndexed { index, user -> user.copy(id = index + 1) }

val workoutsWithId = workouts.mapIndexed { index, workout -> workout.copy(id = index + 1) }
