package ir.mahozad.workout_logger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.mahozad.workout_logger.data.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>
}
