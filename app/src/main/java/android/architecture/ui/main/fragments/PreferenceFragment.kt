package android.architecture.ui.main.fragments

import android.architecture.R
import android.architecture.utils.PreferencesModule
import android.architecture.utils.onTextChange
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_preference.*
import javax.inject.Inject


@AndroidEntryPoint
class PreferenceFragment : Fragment() {
    @Inject
    lateinit var preferencesModule: PreferencesModule

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
        edSetData.onTextChange {
            preferencesModule.setData(it)
        }
        if (preferencesModule.getData()!!.isNotEmpty()) {
            edSetData.setText(preferencesModule.getData())
        }

    }
}