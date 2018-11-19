package br.com.honeyinvestimentos.meusinvestimentos.repository

import br.com.honeyinvestimentos.meusinvestimentos.data.InvestmentsDatabase
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Asset
import io.reactivex.Single
import javax.inject.Inject


class ProductRepository:BaseRepository() {

    @Inject
    lateinit var db:InvestmentsDatabase

    fun loadAllAssets(): Single<List<Asset>> =
        db.assetDao().getAll()

    fun insert(asset: Asset) {
        db.assetDao().insert(asset)
    }

    fun listCategories() =
            db.categoryDao().getAll()

    fun listProducts() =
        db.productDao().getAll()

    fun getProductsByCategory(idCategory: Int) =
        db.productDao().getAllByCategory(idCategory)

    fun updateAssetValue(assetId: Int, newValue: Double) =
         db.assetDao().updateCurrentValue(assetId, newValue)

    fun removeAsset(asset: Asset):Int =
        db.assetDao().delete(asset)



    /*
    fun getAllAssets(): ( InvestmentsDatabase )-> Future<LiveData<Asset>> = {
        db ->
        doAsyncResult {
            db.assetDao().getAll()
        }
    }*/

}