package br.unisinos.a11appsqliteroom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.unisinos.a11appsqliteroom.MainActivity.Companion.meusProdutosArrayList
import br.unisinos.a11appsqliteroom.MainActivity.Companion.produtoDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_produto_cadastro.*

class ProdutoCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_cadastro)
        setSupportActionBar(toolbar)
    }

    fun gravar(view: View) {

        val nomeProduto = edt_nome_produto.editText?.text.toString()
        val descricaoProduto = edt_descricao_produto.editText?.text.toString()
        val quantidadeProduto = edt_quantidade_produto.editText?.text.toString().toInt()

        val produto = Produto(nome = nomeProduto, descricao = descricaoProduto, quantidade = quantidadeProduto)

        produtoDao.inserir(produto)

        meusProdutosArrayList.add(produto)

        finish()
    }

}
