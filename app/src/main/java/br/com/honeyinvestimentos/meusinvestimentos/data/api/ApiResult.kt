package br.com.honeyinvestimentos.meusinvestimentos.data.api


sealed class ApiResult{

    class Stock(val title:String, val description: String):ApiResult()

    class StockQuotes(val referenceDate:String, val closingPrice:Double) :ApiResult()

    class Result(val stock: Stock,
                 val quotes: List<StockQuotes>):ApiResult()
}