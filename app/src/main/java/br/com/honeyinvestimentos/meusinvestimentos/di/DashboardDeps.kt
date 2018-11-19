package br.com.honeyinvestimentos.meusinvestimentos.di

import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel.DashboadViewModel
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel.EditAssetViewModel
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel.NewAssetViewModel
import br.com.honeyinvestimentos.meusinvestimentos.data.InvestmentsDatabase
import br.com.honeyinvestimentos.meusinvestimentos.data.api.BASE_URL
import br.com.honeyinvestimentos.meusinvestimentos.data.api.StockApi
import br.com.honeyinvestimentos.meusinvestimentos.data.room
import br.com.honeyinvestimentos.meusinvestimentos.repository.AssetRepository
import br.com.honeyinvestimentos.meusinvestimentos.repository.ProductRepository
import br.com.honeyinvestimentos.meusinvestimentos.repository.StockQuotesRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
object DashboardModule{

    @Provides
    @Reusable
    @JvmStatic
    fun provideRoomDatabase(): InvestmentsDatabase = room

    @Provides
    @Reusable
    @JvmStatic
    fun provideProductRepository() = ProductRepository()


    @Provides
    @Reusable
    @JvmStatic
    fun provideStockRepository() = StockQuotesRepository()

    @Provides
    @Reusable
    @JvmStatic
    fun provideAssetRepository() = AssetRepository()

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideClientApi(retrofit: Retrofit): StockApi {
        return retrofit.create(StockApi::class.java)
    }

}


@Singleton
@Component(modules = [(DashboardModule::class)])
interface ViewModelInjector{
    fun injectDashboard(dashboardViewModel: DashboadViewModel)
    fun injectNewAsset(newAssetVM: NewAssetViewModel)
    fun injectEditAsset(editAssetViewModel: EditAssetViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun dashboardModule(dashboardModule: DashboardModule): Builder
    }
}


@Singleton
@Component(modules = [(DashboardModule::class)])
interface RepositoryInjector{

    fun injectProduct(productRepository: ProductRepository)
    fun injectStockRep(stockQuotesRepository: StockQuotesRepository)
    fun injectAsset(assetRepository: AssetRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector
        fun dashboardModule(dashboardModule: DashboardModule): Builder
    }

}