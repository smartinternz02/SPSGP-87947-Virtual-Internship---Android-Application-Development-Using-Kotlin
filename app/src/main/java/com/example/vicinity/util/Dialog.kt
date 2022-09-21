package com.example.vicinity.util

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.vicinity.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Dialog(activity: Activity) {

    //Dialog
    var alertDialogBuilderLoading: MaterialAlertDialogBuilder? = null
    var customLayoutLoading: View? = null
    var alertDialogLoading: AlertDialog? = null

    init {

        alertDialogBuilderLoading = MaterialAlertDialogBuilder(activity)
        customLayoutLoading = activity.layoutInflater.inflate(R.layout.dialog_loading, null);
        alertDialogBuilderLoading!!.setCancelable(false)
        alertDialogBuilderLoading!!.setView(customLayoutLoading);
        alertDialogLoading = alertDialogBuilderLoading!!.create()

    }

    fun showDialog(){
        alertDialogLoading!!.show()
    }

    fun dismissDialog(){
        alertDialogLoading!!.dismiss()
    }

}