package br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain

import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Product

data class ProductSumamry(val id:Int,
              val title:String,
              val initials:String,
              val risk:Int,
              val categoryId:Long) {


    companion object {
        fun create(product:Product):ProductSumamry =
            ProductSumamry(
                product.id,
                product.title,
                product.initials,
                product.risk,
                product.categoryId
            )
    }


    override fun toString(): String {
        return  if (initials.isNotEmpty()) initials else title
    }

}
