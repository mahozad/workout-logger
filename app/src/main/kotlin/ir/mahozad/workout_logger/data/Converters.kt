package ir.mahozad.workout_logger.data

import androidx.room.TypeConverter

class SexConverter {
    @TypeConverter
    fun convertToSex(name: String) = Sex.valueOf(name.uppercase())

    @TypeConverter
    fun convertToString(sex: Sex) = sex.name.lowercase().replaceFirstChar(Char::uppercase)
}
