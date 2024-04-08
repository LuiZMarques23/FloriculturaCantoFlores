package com.example.floriculturacantodaflores.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class DB {

    fun salvaDadosUsuarios(nome: String, celula: String){

        val usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val usuarios = hashMapOf(
            "nome" to nome,
            "celula" to celula // celular so um test
        )

        val documentoReference: DocumentReference = db.collection("Usuarios").document(usuarioID)
        documentoReference.set(usuarios).addOnSuccessListener {
            Log.d("DB", "Sucesso ao salvar os dados!")
        }.addOnFailureListener { erro ->
            Log.d("DB_ERROE","Erro ao salvar os dados! ${erro.printStackTrace()}")
        }
    }
}