package br.com.honeyinvestimentos.meusinvestimentos.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String){

    override fun toString(): String {
        return title
    }
}
