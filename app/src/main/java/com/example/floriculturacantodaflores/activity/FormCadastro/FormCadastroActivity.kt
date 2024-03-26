package com.example.floriculturacantodaflores.activity.FormCadastro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.floriculturacantodaflores.R
import com.example.floriculturacantodaflores.databinding.ActivityFormCadastroBinding

class FormCadastroActivity : AppCompatActivity() {

    lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        configcliks()
        corStatusBar()

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