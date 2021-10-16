package android.architecture.api.viewModels

import android.architecture.api.Response
import android.architecture.api.repository.TextRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(var textRepository: TextRepository) : ViewModel() {

    fun fetchData(): LiveData<retrofit2.Response<ArrayList<Response>>> {
        return textRepository.getPosts().asLiveData()
    }

}