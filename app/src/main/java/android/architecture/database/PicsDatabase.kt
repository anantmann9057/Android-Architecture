package android.architecture.database

import android.architecture.api.PicsModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PicsModel::class], version = 1)
abstract class PicsDatabase : RoomDatabase() {
    abstract fun picsDao(): PicsDAO
}