package android.runningapp.data.local.converters

import android.runningapp.domain.model.ActivityType
import androidx.room.TypeConverter

object ActivityTypeConverter {

    @TypeConverter
    fun ActivityType.asString(): String = this.name

    @TypeConverter
    fun String.asActivityType(): ActivityType = ActivityType.valueOf(this)
}