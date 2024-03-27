package com.example.floriculturacantodaflores.activity.FormCadastroActivity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.floriculturacantodaflores.activity.model.DB
import com.example.floriculturacantodaflores.databinding.ActivityFormCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastroActivity : AppCompatActivity() {

    lateinit var binding: ActivityFormCadastroBinding
    val db = DB()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()



        configcliks()
        corStatusBar()
        confcadastro()



    }
    private fun confcadastro(){

        binding.btnCadastra.setOnClickListener {

            val nome = binding.edtNome.text.toString()
            val email = binding.edtemail.text.toString()
            val celula = binding.edtelefone.text.toString()
            val senha = binding.edtConfirmasenha.text.toString()
            val confima = binding.edtconfima.text.toString()

            if (nome.isEmpty() || email.isEmpty() || celula.isEmpty() || senha.isEmpty() || confima.isEmpty()){
                val snackbar = Snackbar.make(it, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()

            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {tarefa ->
                    if (tarefa.isSuccessful){
                        db.salvaDadosUsuarios(nome, celula ) // celular so um test

                        val snackbar = Snackbar.make(it, "Cadastro realizado com sucesso ;)", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.GREEN)
                        snackbar.setTextColor(Color.WHITE)
                        snackbar.show()
                    }
                }.addOnFailureListener { erroCadastro ->
                    val mensageError = when(erroCadastro){
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha no minimo 6 caracteres"
                        is FirebaseAuthUserCollisionException -> "Está conta já foi cadastrada"
                        is FirebaseNetworkException -> "Sem conexão a INTERNET"
                        else -> "Erro ao cadastrar usuário"
                    }

                    val snackbar = Snackbar.make(it, mensageError, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()

                }
            }

        }

    }

    private fun configcliks(){
        binding.btnInicio.setOnClickListener { v ->
            finish()
        }
    }

    private fun corStatusBar() {
        window.statusBarColor = Color.parseColor("#37F818")

    }
}