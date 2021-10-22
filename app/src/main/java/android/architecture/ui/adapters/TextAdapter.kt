package android.architecture.ui.adapters

import android.architecture.api.Response
import android.architecture.databinding.RowJsonPlaceholderBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TextAdapter(
    var jsonPlaceHolderList: retrofit2.Response<ArrayList<Response>>,
) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowJsonPlaceholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowJsonPlaceholderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(jsonPlaceHolderList) {
                binding.tvName.setText(this.body()!!.get(position).title)
            }
        }
    }

    override fun getItemCount(): Int {
        return jsonPlaceHolderList.body()!!.size
    }
}