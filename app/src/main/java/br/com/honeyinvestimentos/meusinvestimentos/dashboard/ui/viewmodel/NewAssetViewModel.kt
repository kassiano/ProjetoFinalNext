package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.CategorySummary
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.ProductSumamry
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Asset
import br.com.honeyinvestimentos.meusinvestimentos.repository.ProductRepository
import br.com.honeyinvestimentos.meusinvestimentos.repository.StockQuotesRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewAssetViewModel:BaseViewModel() {

    @Inject
    lateinit var repository: ProductRepository

    @Inject
    lateinit var stockRepository: StockQuotesRepository

    val categories = MutableLiveData<List<CategorySummary>>()
    val insertAssetResult = MutableLiveData<Boolean>()
    val products = MutableLiveData<List<ProductSumamry>>()

    lateinit var disposeCat: Disposable
    lateinit var disposeInsert: Disposable
    lateinit var disposeProd: Disposable

    init{
        loadCategories()
        insertAssetResult.value = false
    }

    fun loadCategories(){
        disposeCat = repository.listCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { categories->
                categories.map {
                    CategorySummary.create(it)
                }
            }
            .subscribe({result->
                categories.value = result
            },
            {

            })
    }

    fun newAsset(asset: Asset){


        /*
        stockRepository.getStockQuotes(
            asset.name,
            getTotaySumString(-10),
            getTotayString())
        */

        disposeInsert = Completable.fromAction {
            repository.insert(asset)
        }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            insertAssetResult.value = true
        }

    }

    fun getProductsByCategory(idCategory: Int) {
        disposeProd = repository.getProductsByCategory(idCategory)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                    categories->
                categories.map {
                    ProductSumamry.create(it)
                }
            }
            .subscribe({result->
                products.value = result
            },
            {

            })
    }


    override fun onCleared() {
        super.onCleared()

        if(::disposeCat.isInitialized) disposeCat.dispose()
        if(::disposeInsert.isInitialized)  disposeInsert.dispose()
        if(::disposeProd.isInitialized) disposeProd.dispose()
    }

}