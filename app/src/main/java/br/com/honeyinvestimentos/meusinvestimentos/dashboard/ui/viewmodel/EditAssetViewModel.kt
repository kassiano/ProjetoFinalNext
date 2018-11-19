package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.AssetSummary
import br.com.honeyinvestimentos.meusinvestimentos.repository.AssetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditAssetViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: AssetRepository

    val assetSummary = MutableLiveData<AssetSummary>()

    val genericError = MutableLiveData<Boolean>()
    val updateComplete = MutableLiveData<Boolean>()

    lateinit var disposable:Disposable
    lateinit var updateDisposabe:Disposable

    fun loadAsset(assetId:Int){

        disposable = repository.loadAsset(assetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { asset->
                AssetSummary.create(asset)
            }
            .subscribe( {result->

                assetSummary.value = result

            },
            {
                onRetriveError(it)
            })
    }


    fun onRetriveError(error:Throwable){
        genericError.value = true
    }


    fun updateAsset(asset: AssetSummary) {

         updateDisposabe = repository.update(asset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {result->

                updateComplete.value = true
            },
            {
                onRetriveError(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        if(::disposable.isInitialized) disposable.dispose()
        if(::updateDisposabe.isInitialized) updateDisposabe.dispose()
    }
}