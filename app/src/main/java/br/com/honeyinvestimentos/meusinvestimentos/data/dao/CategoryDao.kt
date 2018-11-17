package br.com.honeyinvestimentos.meusinvestimentos.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Category
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Insert
    fun insert(cat: Category):Long

    @Query("select * from category")
    fun getAll(): Single<List<Category>>

}