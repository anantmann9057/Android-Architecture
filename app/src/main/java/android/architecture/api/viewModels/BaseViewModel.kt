package android.architecture.api.viewModels

import android.architecture.api.Response
import android.architecture.api.repository.Repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    fun fetchData(): LiveData<retrofit2.Response<ArrayList<Response>>> {
        return repository.getPosts().asLiveData()
    }
}