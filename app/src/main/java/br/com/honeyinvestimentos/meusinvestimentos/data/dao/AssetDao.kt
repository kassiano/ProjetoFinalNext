package br.com.honeyinvestimentos.meusinvestimentos.data.dao

import android.arch.persistence.room.*
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Asset
import io.reactivex.Single

@Dao
interface AssetDao {

    @Insert
    fun insert(asset: Asset):Long

    @Update
    fun update(asset: Asset):Int

    @Delete
    fun delete(asset: Asset):Int

    @Query("select * from asset")
    fun getAll(): Single<List<Asset>>

    @Query("UPDATE asset set  currentPrice= :currentValue where id = :assetId")
    fun updateCurrentValue(assetId:Int, currentValue:Double) : Int

    @Query("select * from asset where id = :assetId")
    fun getById(assetId: Int): Single<Asset>

}