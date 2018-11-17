package br.com.honeyinvestimentos.meusinvestimentos.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stock_quotes")
class StockQuotes(
    @PrimaryKey(autoGenerate = true)
    val id:Long,

    @ForeignKey( entity = Stock::class ,
        parentColumns = ["id"],
        childColumns =  ["idStock"])
    val idStock:Long,

    val referenceDate:String,
    val closingPrice:Double
)
