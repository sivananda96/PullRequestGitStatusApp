
package com.example.pullrequestgitstatusapp.base

import android.app.Dialog
import android.os.Bundle


import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.pullrequestgitstatusapp.R
import com.example.pullrequestgitstatusapp.di.component.ActivityComponent
import com.example.pullrequestgitstatusapp.di.component.DaggerActivityComponent
import com.example.pullrequestgitstatusapp.di.module.ActivityModule
import com.example.pullrequestgitstatusapp.utils.ext.createProgressDialog
import com.google.android.material.snackbar.Snackbar


/**
 * Acts a Base Activity class for all other [AppCompatActivity]
 */

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: Dialog? = null
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as MainApplication).component)
                .build()
    }

    /**
     * Custom Progress Dialog
     */
    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = createProgressDialog()
            progressDialog?.show()
        }
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    protected fun showToastMessage(message: String?) {
        if (!message.isNullOrEmpty())
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToastMessage(@StringRes stringResourceId: Int) {
        showToastMessage(getString(stringResourceId))
    }

    protected fun showSnackBarMessage(message: String?) {
        if (!message.isNullOrEmpty())
            showSnackBar(message)
    }

    protected fun showSnackBarMessage(@StringRes stringResourceId: Int) {
        showSnackBarMessage(getString(stringResourceId))
    }

    /**
     * Creates a SnackBar for message display
     */
    private fun showSnackBar(message: String?) {
        /*val snackBar = Snackbar.make(findViewById(android.R.id.content),
                message as CharSequence, Snackbar.LENGTH_SHORT)
        val sbView = snackBar.view
        val textView = sbView
                .findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackBar.show()*/
    }

    protected fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.default_error_message))
        }
    }

    protected fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    protected fun setProgress(status: Boolean?) {
        if (status != null && status)
            showProgressDialog()
        else
            dismissProgressDialog()
    }

    override fun onStop() {
        super.onStop()
        dismissProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressDialog()
    }

}
