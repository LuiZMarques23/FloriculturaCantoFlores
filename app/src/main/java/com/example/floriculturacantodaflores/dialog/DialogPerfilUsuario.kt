package com.example.floriculturacantodaflores.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import com.example.floriculturacantodaflores.activity.FormLoginActivity.TelaLoginActivity
import com.example.floriculturacantodaflores.databinding.DialogPerfilUsuarioBinding
import com.example.floriculturacantodaflores.model.DB
import com.google.firebase.auth.FirebaseAuth

class DialogPerfilUsuario(private val activity: Activity) {

    lateinit var dialog:AlertDialog
    lateinit var binding: DialogPerfilUsuarioBinding

    fun iniciaPerfilUsuario(){
        val builder = AlertDialog.Builder(activity)
        binding = DialogPerfilUsuarioBinding.inflate(activity.layoutInflater)
        builder.setCancelable(true)
        builder.setView(binding.root)
        dialog = builder.create()
        dialog.show()
    }

    fun recuperaDadosUsuarioPerfil(){
        val nomeUsuario = binding.txtNomeUsuario
        val emailUsuario = binding.txtEmailUsuario
        val db = DB()
        db.recuperarDadosUsuarioPerfil(nomeUsuario, emailUsuario)

        binding.btDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, TelaLoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
}