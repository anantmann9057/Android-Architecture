package android.architecture.ui.main

import android.architecture.R
import android.architecture.utils.PreferencesModule
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {
    val navController by lazy { Navigation.findNavController(this, R.id.fragmentNav) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
    }

    private fun setUi() {
        setSupportActionBar(tbHome)
        NavigationUI.setupWithNavController(tbHome, navController)

    }


}