package br.com.honeyinvestimentos.meusinvestimentos.repository

import br.com.honeyinvestimentos.meusinvestimentos.di.DaggerRepositoryInjector
import br.com.honeyinvestimentos.meusinvestimentos.di.DashboardModule
import br.com.honeyinvestimentos.meusinvestimentos.di.RepositoryInjector

abstract class BaseRepository {

    private val injector: RepositoryInjector =
        DaggerRepositoryInjector
            .builder()
            .dashboardModule(DashboardModule) .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ProductRepository -> injector.injectProduct(this)
            is StockQuotesRepository-> injector.injectStockRep(this)
        }
    }
}