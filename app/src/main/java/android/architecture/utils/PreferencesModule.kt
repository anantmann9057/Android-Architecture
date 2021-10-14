package android.architecture.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesModule @Inject constructor(@ApplicationContext var context: Context) {
    private val SAMPLE_PREF = "sample_pref"
    private val sampleData = "data"

    private var appSharedPrefs: SharedPreferences =
        context.getSharedPreferences(SAMPLE_PREF, Activity.MODE_PRIVATE)
    private var prefsEditor: SharedPreferences.Editor = appSharedPrefs.edit()

    /* ---------------- ------------------------*/
    fun setData(sampleData: String?) {
        prefsEditor.putString(this.sampleData, sampleData!!).apply()
    }

    fun getData(): String? {
        return appSharedPrefs.getString(sampleData, "Hello")
    }


}