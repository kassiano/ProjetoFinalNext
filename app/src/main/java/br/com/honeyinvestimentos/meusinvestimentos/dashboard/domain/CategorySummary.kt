package br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain

import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Category

data class CategorySummary(val id:Int, val title:String){

    companion object {
        fun create(cat: Category) =
            CategorySummary(
                cat.id,
                cat.title
            )
    }

    override fun toString(): String {
        return title
    }
}