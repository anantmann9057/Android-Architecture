package android.architecture.api.viewModels

import android.architecture.api.PicsModel
import android.architecture.api.repository.PicsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.downloadcoroutines.utils.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PicsViewModel @Inject constructor(var picsRepository: PicsRepository) : ViewModel() {

    val fetchPics = picsRepository.fetchPics()
//    fun fetchPics(): LiveData<ArrayList<PicsModel>> {
//        return picsRepository.fetchPics()
//    }

    fun getPics(
        page: Int? = null,
        limit: Int? = null,
        searchQuery: String? = ""
    ): LiveData<NetworkResource<List<PicsModel>>> {
        return picsRepository.getPics(page, limit, searchQuery).asLiveData()
    }
}