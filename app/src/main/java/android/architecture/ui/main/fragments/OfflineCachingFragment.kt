package android.architecture.ui.main.fragments

import android.architecture.R
import android.architecture.api.PicsModel
import android.architecture.api.viewModels.PicsViewModel
import android.architecture.utils.ImagesAdapter
import android.architecture.utils.showToast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.downloadcoroutines.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_offline_caching.*

@AndroidEntryPoint
class OfflineCachingFragment : Fragment() {
    val picsViewModel: PicsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offline_caching, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    fun setUi() {
        setRecyclerView()
    }

    fun setRecyclerView() {
        picsViewModel.getPics().observe(viewLifecycleOwner) {
            when (it) {

                is NetworkResource.Loading -> {

                }
                is NetworkResource.Error -> {
                    showToast(it.error)
                }
            }
            rvOfflineCaching.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ImagesAdapter(it.data as ArrayList<PicsModel>, requireContext())
                setItemViewCacheSize(it.data.size)
            }

        }
    }
}