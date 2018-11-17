package br.com.honeyinvestimentos.meusinvestimentos.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Product
import io.reactivex.Single

@Dao
interface ProductDao {

    @Insert
    fun insert(product:Product):Long

    @Query("select * from product")
    fun getAll(): List<Product>

    @Query("select * from product where categoryId = :idCategory")
    fun getAllByCategory(idCategory:Int): Single<List<Product>>
}