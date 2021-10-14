package android.architecture.api.service

import android.architecture.api.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    suspend fun getPosts(): retrofit2.Response<ArrayList<Response>>

}