package android.runningapp.data.local.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

object LocalDateConverter {

    @TypeConverter
    fun LocalDateTime.asString(): String = this.toString()

    @TypeConverter
    fun String.asLocalDateTime(): LocalDateTime = LocalDateTime.parse(this)
}