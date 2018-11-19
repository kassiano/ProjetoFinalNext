package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.AssetSummary
import br.com.honeyinvestimentos.meusinvestimentos.repository.ProductRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DashboadViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: ProductRepository

    val allAssetsSumary =  MutableLiveData<List<AssetSummary>>()
    val loadingData = MutableLiveData<Boolean>()
    lateinit var disposable: Disposable
    var isfirstLoad = true

    val genericError = MutableLiveData<Boolean>()

    init {
        firstLoadAssets()
    }

    fun firstLoadAssets() {

        disposable = repository
             .loadAllAssets()
             .delay(1000, TimeUnit.MILLISECONDS)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .doOnSubscribe { onRetriveAssetsStart() }
             //.doOnTerminate { onRetriveAssetsFinish() }
             .map {
                 assets->
                 assets.map {
                     AssetSummary.create(it)
                 }
             }
             .subscribe( {result->
                 onRetriveAssetsSuccess(result)
                 onRetriveAssetsFinish()
                 isfirstLoad = false
             },
             {
                 onRetriveAssetsError(it)
             })

    }

    fun loadAssets(){
        disposable = repository
            .loadAllAssets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { assets->
                assets.map {
                    AssetSummary.create(it)
                }
            }
            .subscribe( {result->
                onRetriveAssetsSuccess(result)
            },
            {
                onRetriveAssetsError(it)
            })
    }


    fun onRetriveAssetsSuccess(assetSumary: List<AssetSummary>){
        allAssetsSumary.value = assetSumary
    }

    fun onRetriveAssetsStart(){
        loadingData.value = true
    }

    fun onRetriveAssetsFinish(){
        loadingData.value = false
    }

    fun onRetriveAssetsError(error:Throwable){
        genericError.value = true
    }


    fun updateAssetValue(assetId: Int, newValue: Double) {

       disposable = Completable.fromAction {
            repository.updateAssetValue(assetId, newValue)
        }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
                loadAssets()
        }
    }



    fun removeAsset(asset: AssetSummary) {

        disposable = Completable.fromAction {

            repository.removeAsset(AssetSummary.createEmptyAssetWithId(asset))

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                loadAssets()
            }

    }

    override fun onCleared() {
        super.onCleared()
        if(::disposable.isInitialized) disposable.dispose()
    }

}