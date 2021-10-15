package android.architecture.database

import android.architecture.api.PicsModel
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PicsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetPics(pics: List<PicsModel>)

    @Query("DELETE FROM pics")
    suspend fun deleteAllPics()

    @Query("SELECT * FROM pics WHERE author LIKE '%' || :searchQuery || '%'")
    fun getPics(searchQuery: String? = ""): Flow<List<PicsModel>>
}