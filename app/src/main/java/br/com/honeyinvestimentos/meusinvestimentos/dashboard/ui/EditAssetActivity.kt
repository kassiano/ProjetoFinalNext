package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.honeyinvestimentos.meusinvestimentos.R
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.AssetSummary
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel.EditAssetViewModel
import br.com.honeyinvestimentos.meusinvestimentos.databinding.ActivityEditAssetBinding
import kotlinx.android.synthetic.main.activity_edit_asset.*

class EditAssetActivity : AppCompatActivity() {

    companion object {
        const val ID_ASSET_EDIT = "ASSET_ID_EDIT"
    }


    val binding by lazy {
        DataBindingUtil.setContentView<ActivityEditAssetBinding>(this,
            R.layout.activity_edit_asset)
    }


    val viewModel : EditAssetViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(EditAssetViewModel::class.java)
    }

    val assetId  by lazy {
        intent.getIntExtra(ID_ASSET_EDIT, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_edit_asset)

        val assetId = intent.getIntExtra(ID_ASSET_EDIT, 0)

        viewModel.loadAsset(assetId)

        viewModel.assetSummary.observe(this, Observer {
            asset->

            asset?.let {
                binding.asset = it
            }

        })

        observeUpdateComplete()
    }

    private fun observeUpdateComplete() {
        viewModel.updateComplete.observe(this, Observer {
            complete->
            complete?.let {
                if(complete) finish()
            }
        })
    }


    fun validateForm():Boolean {

        var isValid = true
        if(edNome.text.isEmpty()){
            edNome.error = getString(R.string.form_error_name)
            isValid = false
        }

        if(edQuantidade.text.isEmpty()){
            edQuantidade.error = getString(R.string.form_error_quantity)
            isValid = false
        }

        if(edPreco.text.isEmpty()){
            edPreco.error = getString(R.string.form_error_price)
            isValid = false
        }

        return isValid
    }

    fun saveAsset(v: View){

        if(!validateForm()) return

        val asset = AssetSummary(assetId,
                 edNome.text.toString(),
                 edPreco.text.toString().toDouble(),
                 etPrecoAtual.text.toString().toDouble(),
                 edQuantidade.text.toString().toInt(),
                0.0,0.0,0.0
            )

        viewModel.updateAsset(asset)
    }
}

