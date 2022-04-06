package ir.mahozad.workout_logger

import ir.mahozad.User
import ir.mahozad.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getAllUsers(): Flow<User> {
        return userDao.getAllUsers()
    }

    suspend fun addUser(user: User) {
        userDao.insert(user)
    }
}
