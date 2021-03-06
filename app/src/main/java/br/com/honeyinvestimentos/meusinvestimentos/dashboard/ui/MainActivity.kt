package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import br.com.honeyinvestimentos.meusinvestimentos.R
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.AssetSummary
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.EditAssetActivity.Companion.ID_ASSET_EDIT
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.adapter.AssetAdapter
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.dialogs.UpdateItemDialog
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel.DashboadViewModel
import br.com.honeyinvestimentos.meusinvestimentos.fp.pipe
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity(),
    UpdateItemDialog.DialogListenner
{
    override fun onDialogPositive(assetId: Int, newValue: Double) {

        doAsync {
            viewModel.updateAssetValue(assetId, newValue)

        }
    }

    val viewModel : DashboadViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(DashboadViewModel::class.java)
    }


    fun onItemClick(asset: AssetSummary){
        startActivity<EditAssetActivity>(ID_ASSET_EDIT to asset.assetId)
    }


    fun onLongClick(asset: AssetSummary):Boolean{

        val opcoes = listOf("Editar", "Excluir")
        selector("O que deseja fazer?", opcoes) { dialogInterface, position ->
            when (position) {
                0 -> {
                    startActivity<EditAssetActivity>(
                        EditAssetActivity.ID_ASSET_EDIT to asset.assetId)
                }
                1-> {
                    viewModel.removeAsset(asset)
                }
            }
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val assetAdapter = AssetAdapter(this,::onItemClick, ::onLongClick)
        rvAssets.adapter = assetAdapter
        rvAssets.layoutManager = LinearLayoutManager(this)
        setHideAndShowScrolledFloatingButton()
        //setListAnimation()

        viewModel.allAssetsSumary.observe(this, Observer {
            assets->

            assets?.let { assetSummary ->

                if(assetSummary.size ==0){
                    emptyListComponents.visibility = VISIBLE
                    rvAssets.visibility = GONE
                }else{
                    assetAdapter.assets = assetSummary
                    emptyListComponents.visibility = GONE
                    rvAssets.visibility = VISIBLE
                }

            }

        })


        fab.setOnClickListener {
            startActivity<NewAssetActivity>()
        }

        observeLoading()
        observeGenericError()
    }

    override fun onResume() {
        super.onResume()
        if(!viewModel.isfirstLoad){
            viewModel.loadAssets()
        }
    }

    private fun observeLoading() {
        viewModel.loadingData.observe(this, Observer {
            loading->

            loading?.let {

                 if(loading){
                     loadingComponents.visibility = VISIBLE
                     layoutComponents.visibility = GONE
                 }else{
                     loadingComponents.visibility = GONE
                     layoutComponents.visibility = VISIBLE
                 }
            }


        })
    }

    private fun observeGenericError(){
        viewModel.genericError.observe(this, Observer {
            error->
            error?.let {

                if(error){
                    alert("Generic Error"){
                        okButton {  }
                    }.show()
                }
            }

        })
    }

    private fun runAnimation(){
        AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation) pipe {

            rvAssets.layoutAnimation = it
            rvAssets.scheduleLayoutAnimation()
        }

    }

    private fun setHideAndShowScrolledFloatingButton() {

        rvAssets.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if( dy > 0 || dy < 0 && fab.isShown)
                    fab.hide()
            }


            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show()
                }

                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

}
