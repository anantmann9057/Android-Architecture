package android.architecture.api.repository

import android.architecture.api.PicsModel
import android.architecture.api.service.ImageApiService
import android.architecture.database.PicsDatabase
import android.architecture.api.paging.Paging
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import androidx.room.withTransaction
import com.example.downloadcoroutines.utils.networkBoundResource
import kotlinx.coroutines.delay
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
    ) = Pager(config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
    ), pagingSourceFactory = { Paging(imageService) }).liveData

//    fun fetchPics(
//        page: Int? = null,
//        limit: Int? = null,
//    ): Flow<ArrayList<PicsModel>> {
//        return flow {
//            emit(imageService.getPics(page, limit))
//        }.catch { e ->
//            Log.e("Pics Exception->", e.localizedMessage)
//        }.flowOn(Dispatchers.IO)
//    }
}