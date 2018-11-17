package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.honeyinvestimentos.meusinvestimentos.R

class EditAssetActivity : AppCompatActivity() {

    companion object {
        const val ID_ASSET_EDIT = "ASSET_ID_EDIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_asset)

        val assetId = intent.getIntExtra(ID_ASSET_EDIT, 0)


    }
}