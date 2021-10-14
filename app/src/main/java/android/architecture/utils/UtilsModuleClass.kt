package android.architecture.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UtilsModuleClass @Inject constructor(@ApplicationContext var context: Context) {
    fun showToast(mssg: Any) {
        Toast.makeText(context, "$mssg", Toast.LENGTH_SHORT).show()
    }

    fun showLog(mssg: Any) {
        Log.e("Logger->", "$mssg")
    }
}