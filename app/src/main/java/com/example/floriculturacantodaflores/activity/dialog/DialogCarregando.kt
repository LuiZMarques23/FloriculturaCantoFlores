package com.example.floriculturacantodaflores.activity.dialog

import android.app.Activity
import android.app.AlertDialog
import com.example.floriculturacantodaflores.R

class DialogCarregando(private val activity: Activity) {

    lateinit var dialog: AlertDialog
    fun iniciaCarregamentoAlerdDialog(){

        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity.layoutInflater
        builder.setView(layoutInflater.inflate(R.layout.dialog_carregando,null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }

    fun liberarAlertDialog(){
        dialog.dismiss()
    }
}