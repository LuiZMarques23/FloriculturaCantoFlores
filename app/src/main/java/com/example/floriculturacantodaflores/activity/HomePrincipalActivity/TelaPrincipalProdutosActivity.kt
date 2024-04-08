package com.example.floriculturacantodaflores.activity.HomePrincipalActivity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.floriculturacantodaflores.R
import com.example.floriculturacantodaflores.activity.FormLoginActivity.TelaLoginActivity
import com.example.floriculturacantodaflores.dialog.DialogPerfilUsuario
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipalProdutosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tela_principal_produtos)
        corStatusBar()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_principal,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.perfil -> iniciarDialogPerfilUsuario()
            R.id.pedidos -> Log.d("p","pedidos")
            R.id.termos -> Log.d("p","termos")
            R.id.ajuda -> Log.d("p","ajuda")
            R.id.deslogar -> deslogarUsuario()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun iniciarDialogPerfilUsuario(){
        val dialogPerfilUsuario = DialogPerfilUsuario(this)
        dialogPerfilUsuario.iniciaPerfilUsuario()
        dialogPerfilUsuario.recuperaDadosUsuarioPerfil()
    }

    private fun deslogarUsuario(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this,TelaLoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun corStatusBar() {
        window.statusBarColor = Color.parseColor("#37F818")

    }
}