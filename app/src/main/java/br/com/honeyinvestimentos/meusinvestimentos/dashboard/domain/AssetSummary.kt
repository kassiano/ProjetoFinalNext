package br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain

import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.calcInvestiment
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.calcPostition
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.calcYield
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Asset

class AssetSummary( val assetId:Int,
                    val name:String,
                    val initialPrice:Double,
                    val currentPrice:Double,
                    val quantity:Int,
                    val investiment:Double,
                    val postition:Double,
                    val yieldValue:Double) {

    companion object {
        fun create(asset: Asset): AssetSummary {
            return AssetSummary(
                asset.id,
                asset.name,
                asset.buyPrice,
                asset.currentPrice,
                asset.quantity,
                calcInvestiment(asset.buyPrice, asset.quantity),
                calcPostition(asset.currentPrice, asset.quantity),
                calcYield(asset.currentPrice, asset.buyPrice)
            )
        }
    }
}


