package br.unisinos.a11appsqliteroom

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Produto::class], version = 1, exportSchema = false)
abstract class BancoDadosBase : RoomDatabase() {

    abstract fun produtoDao() : ProdutoDao

}