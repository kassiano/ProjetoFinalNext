package br.com.honeyinvestimentos.meusinvestimentos.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.Stock
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.StockQuotes
import io.reactivex.Single

@Dao
interface StockDao {

    @Insert
    fun insertStock(stock: Stock):Long

    @Insert
    fun insertStockQuotes(stockQuotes: StockQuotes):Long

    @Query("SELECT * FROM stock where title= :title")
    fun getStock(title:String): List<Stock>


    @Query("SELECT * FROM stock_quotes where idStock= :stockId and referenceDate= :referenceDate")
    fun getStockQuote(stockId:Long, referenceDate:String): Single<StockQuotes>
}