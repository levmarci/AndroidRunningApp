package android.runningapp.data.local.converters

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

object LatLngListConverter {
    @TypeConverter
    fun List<LatLng>.asString(): String {
        return Gson().toJson(this)
    }

    @TypeConverter
    fun String.asLatLngList(): List<LatLng> {
        return Gson().fromJson(this, Array<LatLng>::class.java).toList()
    }
}