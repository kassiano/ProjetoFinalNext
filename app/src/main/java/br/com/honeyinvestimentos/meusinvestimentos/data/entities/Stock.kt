package br.com.honeyinvestimentos.meusinvestimentos.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity( tableName = "stock")
class Stock(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val title:String,
    val description:String
)
