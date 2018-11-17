package br.com.honeyinvestimentos.meusinvestimentos.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "product")
class Product(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val initials:String,
    val risk:Int,

    @ForeignKey( entity = Category::class ,
        parentColumns = ["id"],
        childColumns =  ["categoryId"])
    val categoryId:Long){

    override fun toString(): String {
        return initials
    }
}