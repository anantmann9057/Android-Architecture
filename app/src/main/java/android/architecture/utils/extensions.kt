package android.architecture.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment


fun EditText.onTextChange(callback: (String) -> Unit) {
    callback.invoke(callback.toString())
}

fun Context.showToast(mssg: String?) {
    Toast.makeText(this, "$mssg", Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(mssg: String?) {
    Toast.makeText(requireContext(), "$mssg", Toast.LENGTH_SHORT).show()
}



