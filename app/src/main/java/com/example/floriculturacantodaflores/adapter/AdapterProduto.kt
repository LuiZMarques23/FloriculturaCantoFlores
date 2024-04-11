package com.example.floriculturacantodaflores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.floriculturacantodaflores.databinding.ProdutoItemBinding
import com.example.floriculturacantodaflores.model.Produto


class AdapterProduto(val lista_produtos: MutableList<Produto>):
    RecyclerView.Adapter<AdapterProduto.ProdutoViewHolter>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolter {
        val item_lista = ProdutoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProdutoViewHolter(item_lista)
    }


    override fun onBindViewHolder(holder: ProdutoViewHolter, position: Int) {
        lista_produtos.get(position).foto?.let { holder.fotoProduto.setImageResource(it) }
        holder.nomeProduto.text = lista_produtos.get(position).nome
        holder.precoProduto.text = lista_produtos.get(position).preco
    }

    override fun getItemCount() = lista_produtos.size

    inner class ProdutoViewHolter(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val fotoProduto = binding.imagemProduto
        val nomeProduto = binding.textNomeProduto
        val precoProduto = binding.txtValorProduto

    }
}