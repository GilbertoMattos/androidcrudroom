package br.unisinos.a11appsqliteroom

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM produto")
    fun selecionarTodos() : List<Produto>

    @Query("SELECT * FROM produto WHERE id = :idParam")
    fun selecionarProduto(idParam : Int) : Produto

    @Insert
    fun inserir(vararg produto: Produto)

    @Delete
    fun remover(vararg produto: Produto)

}
