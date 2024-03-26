package com.example.floriculturacantodaflores.activity.FormLogin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.floriculturacantodaflores.activity.FormCadastro.FormCadastroActivity
import com.example.floriculturacantodaflores.databinding.ActivityTelaLoginBinding

class TelaLoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityTelaLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        corStatusBar()
        configclik()

        supportActionBar!!.hide()



    }
    private fun configclik(){
        binding.btnCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun corStatusBar() {
        window.statusBarColor = Color.parseColor("#37F818")
    }
}