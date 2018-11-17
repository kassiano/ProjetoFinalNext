package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import br.com.honeyinvestimentos.meusinvestimentos.R
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.CategorySummary
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.ProductSumamry
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.viewmodel.NewAssetViewModel
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.dateToLong
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.setData
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Asset
import br.com.honeyinvestimentos.meusinvestimentos.fp.pipe
import kotlinx.android.synthetic.main.activity_new_asset.*
import java.util.*

class NewAssetActivity : AppCompatActivity() ,
    AdapterView.OnItemSelectedListener{

    val viewModel : NewAssetViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(NewAssetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_asset)

        spCategoria.setOnItemSelectedListener(this)
        observeCategories()
        observeProducts()
        observeInsertResult()
    }

    private fun observeInsertResult() {
        viewModel.insertAssetResult.observe(this, Observer {
            result->
            result?.let {
                if(result){
                    finish()
                }
            }
        })
    }


    fun observeCategories(){

        viewModel.categories.observe(this, Observer {
            categories->

            categories?.let {
                spCategoria.setData(categories)
            }
        })
    }

    fun observeProducts(){
        viewModel.products.observe(this, Observer {
            products->
            products?.let {
                spProduto.setData(products)
            }
        })
    }


    fun saveAsset(v:View){

        if(!validateForm()) return

        val produto =  spProduto.selectedItem as ProductSumamry

        val asset = Asset(0,
                            edNome.text.toString(),
                            dateToLong(Calendar.getInstance()),
                            produto.id,
                            edQuantidade.text.toString().toInt(),
                            edPreco.text.toString().toDouble(),
                            edPreco.text.toString().toDouble()
                        )

        viewModel.newAsset(asset)

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

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

        (parent.getItemAtPosition(position) as CategorySummary) pipe {

            viewModel.getProductsByCategory(it.id)
        }

    }



}
