package br.com.honeyinvestimentos.meusinvestimentos.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "asset")
data class Asset(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,

    val buyDate:Long,

    @ForeignKey( entity = Product::class ,
                 parentColumns = ["id"],
                 childColumns =  ["productId"])
    val productId:Int,

    val quantity:Int,

    val buyPrice:Double,

    val currentPrice:Double

)