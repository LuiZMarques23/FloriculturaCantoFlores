package com.example.floriculturacantodaflores.activity.DetalheProdutos

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.floriculturacantodaflores.R
import com.example.floriculturacantodaflores.activity.Pagamento.PagamentoActivity
import com.example.floriculturacantodaflores.databinding.ActivityDetalheProdutosBinding

class DetalheProdutosActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetalheProdutosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        corStatusBar()
        confiCliks()
        funcao()
    }

    private fun funcao(){

        val foto = intent.extras?.getString("foto")
        val nome = intent.extras?.getString("nome")
        val preco = intent.extras?.getString("preco")

        Glide.with(this).load(foto).into(binding.dtFotoProduto)
        binding.dtNomeProduto.text = nome
        binding.dtPrecoProduto.text = "R$ ${preco}"

        binding.btnFinalizarPedido.setOnClickListener {

            val intent = Intent(this,PagamentoActivity::class.java)
            intent.putExtra("nome",nome)
            intent.putExtra("preco",preco)
            startActivity(intent)


        }

    }
    private fun confiCliks(){

        binding.btnCancelaFnalizarPedido.setOnClickListener {
            finish()
        }
    }

    private fun corStatusBar(){
        window.statusBarColor = Color.parseColor("#37F818")
    }

}