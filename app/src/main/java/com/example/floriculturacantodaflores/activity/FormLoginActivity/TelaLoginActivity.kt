package com.example.floriculturacantodaflores.activity.FormLoginActivity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.floriculturacantodaflores.activity.FormCadastroActivity.FormCadastroActivity
import com.example.floriculturacantodaflores.activity.HomePrincipalActivity.TelaPrincipalProdutosActivity
import com.example.floriculturacantodaflores.databinding.ActivityTelaLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class TelaLoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityTelaLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        corStatusBar()
        configclik()
        logarConta()

        supportActionBar!!.hide()

    }

    private fun logarConta(){
        binding.btnEntrar.setOnClickListener { view ->

            val email = binding.edtEmail.text.toString()
            val senha = binding.edtsenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os dados", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            }else{
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener { tarefa ->
                    if (tarefa.isSuccessful){



                        val intent = Intent(this, TelaPrincipalProdutosActivity:: class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener {
                    val snackbar = Snackbar.make(view,"Erro ao fazer login!", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                }
            }
        }
    }
    private fun configclik(){

        binding.btnRecuraSenha.setOnClickListener {
            val intent = Intent(this, RecuperaContaActivity:: class.java)
            startActivity(intent)
        }

        binding.btnCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun corStatusBar() {
        window.statusBarColor = Color.parseColor("#37F818")
    }
}