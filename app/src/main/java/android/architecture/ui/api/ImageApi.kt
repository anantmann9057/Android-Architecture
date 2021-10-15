package android.architecture.ui.api

import android.architecture.R
import android.architecture.api.viewModels.PicsViewModel
import android.architecture.utils.ImagesAdapter
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
        setImagesAdapter()
    }

    fun setImagesAdapter() {
        imageViewModel.fetchPics().observe(viewLifecycleOwner) {
                rvImages.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ImagesAdapter(it, requireContext())
                    setItemViewCacheSize(it.size)
                }
        }
    }
}