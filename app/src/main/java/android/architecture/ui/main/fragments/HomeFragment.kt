package android.architecture.ui.main.fragments

import android.architecture.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    val controller by lazy { Navigation.findNavController(requireActivity(), R.id.fragmentNav) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    fun setupNavigation() {
        btApi.setOnClickListener {
            controller.navigate(R.id.action_homeFragment_to_apiFragment)
        }
        btInjection.setOnClickListener {
            controller.navigate(R.id.action_homeFragment_to_injectionFragment)

        }
        btPreferences.setOnClickListener {
            controller.navigate(R.id.action_homeFragment_to_preferenceFragment)
        }
    }


}