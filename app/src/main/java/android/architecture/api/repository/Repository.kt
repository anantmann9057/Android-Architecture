package android.architecture.api.repository

import android.architecture.api.Response
import android.architecture.api.service.ApiService
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(var apiService: ApiService) {

    fun getPosts(): Flow<retrofit2.Response<ArrayList<Response>>> {
        return flow {
            emit(apiService.getPosts())
        }.catch { e ->
            Log.e("Posts Exception->", e.localizedMessage)
        }.flowOn(Dispatchers.IO)
    }
}