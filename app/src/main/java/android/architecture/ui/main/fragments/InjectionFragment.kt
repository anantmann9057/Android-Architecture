package android.architecture.ui.main.fragments

import android.architecture.R
import android.architecture.ui.main.MainActivity
import android.architecture.utils.UtilsModuleClass
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_injection.*
import javax.inject.Inject

@AndroidEntryPoint
class InjectionFragment : Fragment() {
    @Inject
    lateinit var mainActivity: MainActivity

    @Inject
    lateinit var utilsModuleClass: UtilsModuleClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_injection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    fun setUi() {
        btFetchInjection.setOnClickListener {
            utilsModuleClass.showToast("Toast from injected module")
        }
        btLog.setOnClickListener {
            utilsModuleClass.showLog("Log from injected module")
        }
    }
}