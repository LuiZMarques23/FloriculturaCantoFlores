package com.example.floriculturacantodaflores.model

import android.util.Log
import android.widget.TextView
import com.example.floriculturacantodaflores.adapter.AdapterProduto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class DB {

    fun salvaDadosUsuarios(nome: String){

        val usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val usuarios = hashMapOf(
            "nome" to nome
        )

        val documentoReference: DocumentReference = db.collection("Usuarios").document(usuarioID)
        documentoReference.set(usuarios).addOnSuccessListener {
            Log.d("DB", "Sucesso ao salvar os dados!")
        }.addOnFailureListener { erro ->
            Log.d("DB_ERROE","Erro ao salvar os dados! ${erro.printStackTrace()}")
        }
    }

    fun recuperarDadosUsuarioPerfil(nomeUsuario: TextView, emailUsuario: TextView){

        val usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val email = FirebaseAuth.getInstance().currentUser!!.email
        val db = FirebaseFirestore.getInstance()

        val documentoReference: DocumentReference = db.collection("Usuarios").document(usuarioID)
        documentoReference.addSnapshotListener { documento, error ->
            if (documento != null){
                nomeUsuario.text = documento.getString("nome")
                emailUsuario.text = email
            }
        }

    }

    fun obterListaProdutos(lista_produtos: MutableList<Produto>, adapter_produtos: AdapterProduto){

        val db = FirebaseFirestore.getInstance()

        db.collection("Produtos").get()
            .addOnCompleteListener { tarefa ->
                if (tarefa.isSuccessful){
                    for (documento in tarefa.result!!){

                        val produto = documento.toObject(Produto::class.java)
                        lista_produtos.add(produto)
                        adapter_produtos.notifyDataSetChanged()

                    }
                }
            }

    }
}