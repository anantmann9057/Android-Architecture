package android.architecture.ui.main.fragments

import android.architecture.R
import android.architecture.api.dataStore.StateManager
import android.architecture.utils.PreferencesModule
import android.architecture.utils.showToast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_preference.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PreferenceFragment : Fragment() {
    @Inject
    lateinit var preferencesModule: PreferencesModule

    @Inject
    lateinit var stateManager: StateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preference, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    fun setUi() {
        btSetPref.setOnClickListener {
            CoroutineScope(IO).launch {
                stateManager.storeData(edSetData.text.toString())
            }
            showToast("Data Store Updated")
        }
        stateManager.dataFlow.asLiveData().observe(viewLifecycleOwner) {
            edSetData.setText(it)
        }

    }
}