package android.architecture.ui.main.fragments

import android.app.Activity
import android.architecture.R
import android.architecture.api.service.ApiService
import android.architecture.api.viewModels.BaseViewModel
import android.architecture.utils.MyAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_api.*
import javax.inject.Inject

@AndroidEntryPoint
class ApiFragment @Inject constructor() : Fragment() {
    val viewModels: BaseViewModel by viewModels()


    @Inject
    lateinit var mainActivity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    fun setUi() {
        fetchApiData()
    }

    fun fetchApiData() {
        viewModels.fetchData().observe(viewLifecycleOwner) {
            tvUrl.append("Base Url-> ${ApiService.BASE_URL}")
            tvData.apply {
                layoutManager = LinearLayoutManager(mainActivity)
                adapter = MyAdapter(it)
            }
        }
    }

}