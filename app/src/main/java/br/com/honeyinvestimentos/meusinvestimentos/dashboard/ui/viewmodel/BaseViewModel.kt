package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.honeyinvestimentos.meusinvestimentos.di.DaggerViewModelInjector
import br.com.honeyinvestimentos.meusinvestimentos.di.DashboardModule
import br.com.honeyinvestimentos.meusinvestimentos.di.ViewModelInjector

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector =
        DaggerViewModelInjector
            .builder()
            .dashboardModule(DashboardModule) .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is DashboadViewModel -> injector.injectDashboard(this)
            is NewAssetViewModel -> injector.injectNewAsset( this )
            is EditAssetViewModel -> injector.injectEditAsset(this)
        }
    }

}