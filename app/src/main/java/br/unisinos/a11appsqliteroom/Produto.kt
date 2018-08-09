package br.unisinos.a11appsqliteroom

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Produto (
       @PrimaryKey(autoGenerate = true)
       val id: Long = 0,
       val nome : String,
       val descricao: String,
       val quantidade : Int
)