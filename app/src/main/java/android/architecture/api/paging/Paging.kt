package android.architecture.api.paging

import android.architecture.api.PicsModel
import android.architecture.api.service.ImageApiService
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val LOREMPICSUM_PAGE_INDEX = 1

class Paging(val imageApiService: ImageApiService) : PagingSource<Int, PicsModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicsModel> {
        val position = params.key ?: LOREMPICSUM_PAGE_INDEX
        return try {
            val response = imageApiService.getPics(position, params.loadSize)
            val pics = response
            LoadResult.Page(
                data = pics,
                prevKey = if (position == LOREMPICSUM_PAGE_INDEX) null else position - 1,
                nextKey = if (pics.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)

        }

    }

    override fun getRefreshKey(state: PagingState<Int, PicsModel>): Int? {
        TODO("Not yet implemented")
    }

}