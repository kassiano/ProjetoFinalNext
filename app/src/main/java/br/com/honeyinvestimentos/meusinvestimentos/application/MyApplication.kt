package br.com.honeyinvestimentos.meusinvestimentos.application

import android.app.Application
import br.com.honeyinvestimentos.meusinvestimentos.data.initDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase(this)
    }
}