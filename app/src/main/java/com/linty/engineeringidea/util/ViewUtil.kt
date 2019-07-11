package com.linty.engineeringidea.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.linty.engineeringidea.R

/**
 * ViewUtil is a class for action with view
 */
class ViewUtil {
    companion object {
        /**
         * showAlertDialog is a method for showing alert dialog
         * @param message string message for displayinf on the alert dialog
         * @param context the context
         */
        fun showAlertDialog(message: String, context: Context) {
            AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.ok), null)
                .show()
        }

        /**
         * hideView is a method for hiding view
         * @param view view for own hiding
         */
        fun hideView(view: View) {
            view.visibility = View.GONE
        }
    }
}