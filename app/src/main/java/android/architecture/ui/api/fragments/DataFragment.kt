package android.architecture.ui.api.fragments

import android.architecture.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_data.*

class DataFragment : Fragment() {
    val controller by lazy {
        Navigation.findNavController(requireActivity(), R.id.fragmentApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    fun setUi() {
        btImagesApi.setOnClickListener {
            controller.navigate(R.id.action_dataFragment_to_imageApi)

        }
        btTextApi.setOnClickListener {
            controller.navigate(R.id.action_dataFragment_to_textApi)

        }
    }

}