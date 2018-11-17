@file:JvmName("ApiResultKt")

package br.com.honeyinvestimentos.meusinvestimentos.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val BASE_URL:String = "http://68.183.22.237/"

interface StockApi{

    @GET("/api/stockQuotes/{assetName}?")
    fun getStockQuote(
        @Path("assetName") assetName:String,
        @Query("startDate") startDate:String,
        @Query("endDate") endDate:String): Observable<ApiResult.Result>

}