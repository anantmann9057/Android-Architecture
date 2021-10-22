package android.architecture.ui.adapters

import android.architecture.api.PicsModel
import android.architecture.databinding.RowLoremPicsumBinding
import android.architecture.utils.loadUrl
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class ImagesAdapter @Inject constructor() :
    PagingDataAdapter<PicsModel, ImagesAdapter.ViewHolder>(PICS_COMPARATOR) {

    class ViewHolder(private val binding: RowLoremPicsumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pics: PicsModel) {
            binding.apply {
                imageViewPics.loadUrl(pics.downloadUrl!!)
                textViewPics.setText(pics.author)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowLoremPicsumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    companion object {
        private val PICS_COMPARATOR = object : DiffUtil.ItemCallback<PicsModel>() {
            override fun areItemsTheSame(oldItem: PicsModel, newItem: PicsModel): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: PicsModel, newItem: PicsModel): Boolean =
                oldItem == newItem

        }
    }


}