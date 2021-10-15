package android.architecture.utils

import android.architecture.api.PicsModel
import android.architecture.databinding.RowLoremPicsumBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import javax.inject.Inject

class ImagesAdapter @Inject constructor(
    var loremPicsumList: ArrayList<PicsModel>,
    var context: Context
) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowLoremPicsumBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowLoremPicsumBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(loremPicsumList) {
                Glide.with(context).load(this.get(position).downloadUrl)
                    .fitCenter().into(binding.imageViewPics)
                binding.textViewPics.setText(this.get(position).downloadUrl)
            }
        }
    }

    override fun getItemCount(): Int {
        return loremPicsumList.size
    }
}