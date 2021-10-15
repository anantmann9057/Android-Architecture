package android.architecture.api.service

import android.architecture.api.PicsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    companion object {
        const val IMAGE_BASE_URL = "https://picsum.photos/v2/"
    }

    @GET("list")
    suspend fun getPics(
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
    ): ArrayList<PicsModel>
}