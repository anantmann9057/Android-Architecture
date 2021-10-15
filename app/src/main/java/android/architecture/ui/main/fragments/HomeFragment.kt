package android.architecture.ui.main.fragments

import android.architecture.R
import android.architecture.utils.PreferencesModule
import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var preferences: PreferencesModule
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
        setUi()

    }

    fun setUi() {
        if (requireContext().isDarkThemeOn()) {
            btDarkMode.isChecked = true
        }
        setupNavigation()
    }

    fun setupNavigation() {
        btApi.setOnClickListener {
            controller.navigate(R.id.action_homeFragment_to_apiFragment)
        }

        btBundle.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("data", "Data Passed From Bundle")
            controller.navigate(R.id.action_homeFragment_to_bundleFragment, bundle)
        }

        btInjection.setOnClickListener {
            controller.navigate(R.id.action_homeFragment_to_injectionFragment)
        }

        btPreferences.setOnClickListener {
            controller.navigate(R.id.action_homeFragment_to_preferenceFragment)
        }

        btOfflineCaching.setOnClickListener {
            controller.navigate(R.id.action_homeFragment_to_offlineCachingFragment)
        }


        btDarkMode.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                preferences.setIsDarkMode(true)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                preferences.setIsDarkMode(false)

            }
        }
    }

    fun Context.isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }

}