package br.unisinos.a11appsqliteroom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.lista_produto_item.view.*

class ProdutoListaAdapter (
        private val produtos : MutableList<Produto> = mutableListOf(),
        private val context: Context) : RecyclerView.Adapter<ProdutoListaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val createdView = LayoutInflater.from(context).inflate(R.layout.lista_produto_item, parent, false)
        return  ViewHolder(createdView)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, posicao: Int) {
        val produto = produtos[posicao]
        holder.bind(produto)
    }

    fun adicionarTodosProdutos(produtos: List<Produto>) {
        this.produtos.addAll(produtos)
        notifyItemRangeInserted(0, produtos.size)
    }

    fun substituirTodosProdutos(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nomeProduto : TextView = itemView.txv_item_nome_produto
        private val descricaoProduto : TextView = itemView.txv_item_descricao_produto
        private val quantidadeProduto : TextView = itemView.txv_item_quantidade_produto

        fun bind(produto: Produto) {
            nomeProduto.text = "${produto.id}-${produto.nome}"
            descricaoProduto.text = produto.descricao
            quantidadeProduto.text = produto.quantidade.toString()
        }
    }


}
