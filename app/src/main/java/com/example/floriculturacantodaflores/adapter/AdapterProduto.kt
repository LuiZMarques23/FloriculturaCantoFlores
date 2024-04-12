package com.example.floriculturacantodaflores.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.floriculturacantodaflores.activity.DetalheProdutos.DetalheProdutosActivity
import com.example.floriculturacantodaflores.databinding.ProdutoItemBinding
import com.example.floriculturacantodaflores.model.Produto


class AdapterProduto(val context: Context, val lista_produtos: MutableList<Produto>):
    RecyclerView.Adapter<AdapterProduto.ProdutoViewHolter>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolter {
        val item_lista = ProdutoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProdutoViewHolter(item_lista)
    }


    override fun onBindViewHolder(holder: ProdutoViewHolter, position: Int) {
        Glide.with(context).load(lista_produtos.get(position).foto).into(holder.fotoProduto)
        holder.nomeProduto.text = lista_produtos.get(position).nome
        holder.precoProduto.text = "R$ ${lista_produtos.get(position).preco}"

        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetalheProdutosActivity::class.java)
            intent.putExtra("foto",lista_produtos.get(position).foto)
            intent.putExtra("nome",lista_produtos.get(position).nome)
            intent.putExtra("preco",lista_produtos.get(position).preco)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = lista_produtos.size

    inner class ProdutoViewHolter(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val fotoProduto = binding.imagemProduto
        val nomeProduto = binding.textNomeProduto
        val precoProduto = binding.txtValorProduto

    }
}