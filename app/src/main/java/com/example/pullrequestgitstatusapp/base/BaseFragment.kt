package com.example.pullrequestgitstatusapp.base

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.pullrequestgitstatusapp.R
import com.example.pullrequestgitstatusapp.base.BaseActivity
import com.example.pullrequestgitstatusapp.di.component.ActivityComponent
import com.example.pullrequestgitstatusapp.utils.ext.createProgressDialog
import com.google.android.material.snackbar.Snackbar

/**
 * Acts as a Base Fragment class for all other fragments in app
 */

abstract class BaseFragment : Fragment() {

    private var progressDialog: Dialog? = null
    protected lateinit var activityComponent: ActivityComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.activityComponent = context.activityComponent
        }
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
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
        if (activity != null) {
           /* val snackBar = Snackbar.make(activity!!.findViewById(android.R.id.content),
                    message as CharSequence, Snackbar.LENGTH_SHORT)
            val sbView = snackBar.view
            val textView = sbView
                    .findViewById<View>(androidx.design.R.id.snackbar_text) as TextView
            textView.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
            snackBar.show()*/
        }
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

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressDialog()
    }

}
