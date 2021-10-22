package android.architecture.ui.api.fragments

import android.architecture.R
import android.architecture.api.service.ImageApiService
import android.architecture.api.viewModels.PicsViewModel
import android.architecture.ui.adapters.ImagesAdapter
import android.architecture.api.paging.PicsLoadStateAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image_api.*


@AndroidEntryPoint
class ImageApi : Fragment() {
    val imageViewModel: PicsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    fun setUi() {
        tvUrl.setText("Base Url-> ${ImageApiService.IMAGE_BASE_URL}")
        setImagesAdapter()
    }

    fun setImagesAdapter() {
        val adapter = ImagesAdapter()
        imageViewModel.fetchPics.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            rvImages.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PicsLoadStateAdapter { adapter.retry() },
                footer = PicsLoadStateAdapter { adapter.retry() }
            )
            rvImages.layoutManager = LinearLayoutManager(requireContext())
            rvImages.setItemViewCacheSize(200)
        }

    }
}