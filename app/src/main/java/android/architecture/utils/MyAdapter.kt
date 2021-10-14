package android.architecture.utils

import android.architecture.api.Response
import android.architecture.databinding.RowJsonPlaceholderBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    var jsonPlaceHolderList: retrofit2.Response<ArrayList<Response>>,
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
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