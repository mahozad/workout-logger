package ir.mahozad.workout_logger.user

import ir.mahozad.workout_logger.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getAllUsers() = userDao.getAllUsers()

    suspend fun addUser(user: User) = withContext(Dispatchers.IO) { userDao.insert(user) }
}
