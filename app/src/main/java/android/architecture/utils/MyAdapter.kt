package android.architecture.utils

import android.architecture.api.Response
import android.architecture.databinding.RowJsonPlaceholderBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    var jsonPlaceHolderList: retrofit2.Response<ArrayList<Response>>,
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: RowJsonPlaceholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowJsonPlaceholderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item of the list languageList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(jsonPlaceHolderList) {
                binding.tvName.setText(this.body()!!.get(position).title)
            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return jsonPlaceHolderList.body()!!.size
    }
}