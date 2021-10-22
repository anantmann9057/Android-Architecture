package android.architecture.ui.navigation

import android.architecture.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_navigation_example.*

class NavigationExampleActivity : AppCompatActivity() {
    val navController by lazy { Navigation.findNavController(this, R.id.navigationFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_example)
        setUi()
    }


    private fun setUi() {
        NavigationUI.setupWithNavController(bottomNav, navController)
    }
}