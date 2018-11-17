package br.com.honeyinvestimentos.meusinvestimentos.repository

import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.TaxIndex
import io.reactivex.Observable


class TaxIndexRepository: BaseRepository() {

    private val allIndices = listOf<TaxIndex>(
        TaxIndex("CDI", 6.40),
        TaxIndex("IPC-A", 4.30)
    )

    fun getAllTaxIndex() = Observable.just(allIndices)

    fun getIndiceByTitle(title: String) =
        Observable.just(
                allIndices.filter {
                    it.title == title
                }.firstOrNull()
        )
}