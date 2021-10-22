package android.architecture.utils

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


fun EditText.onTextChange(callback: (String) -> Unit) {
    callback.invoke(callback.toString())
}

fun Context.showToast(mssg: Any?) {
    Toast.makeText(this, "$mssg", Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(mssg: Any?) {
    Toast.makeText(requireContext(), "$mssg", Toast.LENGTH_SHORT).show()
}

fun showLog(mssg: Any?) {
    Log.e("Logs->", "$mssg")
}

fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade()).into(this)
}


