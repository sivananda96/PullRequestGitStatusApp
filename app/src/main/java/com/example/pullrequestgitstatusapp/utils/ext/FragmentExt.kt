package com.example.pullrequestgitstatusapp.utils.ext

import android.app.Activity
import android.app.Dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.pullrequestgitstatusapp.R


/********************************************
 * Fragment Extension Functions
 *********************************************/

/**
 * Extension function to create a view model
 */
fun <T : ViewModel> Fragment.getViewModel(modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null): T {
    return viewModelFactory?.let { ViewModelProviders.of(this, it).get(modelClass) }
            ?: ViewModelProviders.of(this).get(modelClass)
}

/**
 * Creates a Progress Dialog
 * @return
 */
fun Fragment.createProgressDialog(): Dialog {
    val progressDialog = Dialog(context!!)
    progressDialog.setContentView(R.layout.progress_dialog)
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    return progressDialog
}

/**
 * Extension function to hide the keyboard
 */
fun Fragment.hideKeyboard() {
    val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity?.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}