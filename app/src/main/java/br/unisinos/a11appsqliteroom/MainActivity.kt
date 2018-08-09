package br.unisinos.a11appsqliteroom

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.lista_produto_item.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        internal val nomeBancoDados : String = "nomebancodedados"
        internal lateinit var produtoDao: ProdutoDao
        internal lateinit var meusProdutosArrayList: ArrayList<Produto>
        internal lateinit var produtoAdapter: ProdutoListaAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val db = Room.databaseBuilder(this,
                        BancoDadosBase::class.java,
                        Companion.nomeBancoDados).allowMainThreadQueries().build()

        produtoDao = db.produtoDao()

        meusProdutosArrayList = carregarProdutos()

        configurarComportamentoListaRecyclerView(meusProdutosArrayList)

        fab.setOnClickListener { _ ->
            val produtoCadastro = Intent(this, ProdutoCadastroActivity::class.java)
            startActivity(produtoCadastro)
        }
    }

    override fun onResume() {
        super.onResume()
        produtoAdapter.substituirTodosProdutos(produtoDao.selecionarTodos())
    }

    private fun configurarComportamentoListaRecyclerView(meusProdutosArrayList: ArrayList<Produto>) {

        rcv_lista_produtos.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        produtoAdapter = ProdutoListaAdapter(meusProdutosArrayList, context = this)

        rcv_lista_produtos.adapter = produtoAdapter

        val helper = ItemTouchHelper(
                object : ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                        var nomeProduto:String = viewHolder.itemView.txv_item_nome_produto.text.toString()
                        var idProduto = nomeProduto.substring(0, nomeProduto.indexOf('-'))

                        val prod = produtoDao.selecionarProduto(Integer.parseInt(idProduto))
                        produtoDao.remover(prod)

                        // atualizar a tela
                        var posicao = viewHolder.adapterPosition
                        meusProdutosArrayList.removeAt(posicao)
                        produtoAdapter.notifyItemRemoved(posicao)
                    }
                }
        )

        helper.attachToRecyclerView(rcv_lista_produtos)
    }

    private fun carregarProdutos(): ArrayList<Produto> {
        return produtoDao.selecionarTodos() as ArrayList<Produto>
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
