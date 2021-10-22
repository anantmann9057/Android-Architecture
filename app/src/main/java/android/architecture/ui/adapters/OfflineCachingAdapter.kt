package android.architecture.ui.adapters

import android.architecture.api.PicsModel
import android.architecture.databinding.RowLoremPicsumBinding
import android.architecture.utils.loadUrl
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OfflineCachingAdapter(var data: List<PicsModel>) :
    RecyclerView.Adapter<OfflineCachingAdapter.viewHolder>() {

    class viewHolder(private val binding: RowLoremPicsumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pics: PicsModel) {
            binding.apply {
                imageViewPics.loadUrl(pics.downloadUrl!!)
                textViewPics.setText(pics.author)
            }
        }
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        if (currentItem != null) holder.bind(currentItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding =
            RowLoremPicsumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}