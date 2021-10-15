package android.architecture.api.repository

import android.architecture.api.PicsModel
import android.architecture.api.service.ImageApiService
import android.architecture.database.PicsDatabase
import android.util.Log
import androidx.room.withTransaction
import com.example.downloadcoroutines.utils.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class PicsRepository @Inject constructor(
    @Named("images_api") var imageService: ImageApiService,
    @Named("pics_database") val picsDatabase: PicsDatabase
) {
    val picsDAO = picsDatabase.picsDao()
    fun getPics(page: Int? = null, limit: Int? = null, searchQuery: String? = "") =
        networkBoundResource(
            query = {
                picsDAO.getPics(searchQuery)
            },
            fetch = {
                delay(2000)
                imageService.getPics(page, limit)
            },
            saveFetchResult = { pics ->
                picsDatabase.withTransaction {
                    picsDAO.deleteAllPics()
                    picsDAO.insetPics(pics as List<PicsModel>)
                }
            }
        )

    fun fetchPics(
        page: Int? = null,
        limit: Int? = null,
    ): Flow<ArrayList<PicsModel>> {
        return flow {
            emit(imageService.getPics(page, limit))
        }.catch { e ->
            Log.e("Pics Exception->", e.localizedMessage)
        }.flowOn(Dispatchers.IO)
    }
}