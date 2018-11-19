package br.com.honeyinvestimentos.meusinvestimentos.repository

import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.AssetSummary
import br.com.honeyinvestimentos.meusinvestimentos.data.InvestmentsDatabase
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Asset
import io.reactivex.Observable.just
import javax.inject.Inject

class AssetRepository: BaseRepository() {

    @Inject
    lateinit var db: InvestmentsDatabase

    fun loadAsset(assetId:Int)=
        db.assetDao().getById(assetId)

    fun update(asset: AssetSummary) =
        db.assetDao().getById(asset.assetId)
            .map {

                val assetToUpdate = Asset(it.id,
                    asset.name,
                    it.buyDate,
                    it.productId,
                    asset.quantity,
                    asset.initialPrice,
                    asset.currentPrice)

                just( db.assetDao().update(assetToUpdate))

            }


}