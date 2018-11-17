package br.com.honeyinvestimentos.meusinvestimentos.repository

import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.dateSum
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.dateToString
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.stringToCalendar
import br.com.honeyinvestimentos.meusinvestimentos.data.InvestmentsDatabase
import br.com.honeyinvestimentos.meusinvestimentos.data.api.ApiResult
import br.com.honeyinvestimentos.meusinvestimentos.data.api.StockApi
import br.com.honeyinvestimentos.meusinvestimentos.fp.pipe
import io.reactivex.Observable
import javax.inject.Inject

class StockQuotesRepository: BaseRepository() {

    @Inject
    lateinit var api: StockApi

    @Inject
    lateinit var db: InvestmentsDatabase


    private fun getLastQuote(assetName:String, referenceDate:String):
            Observable<ApiResult.StockQuotes> =

        //"2018-11-20"
        //"2018-11-10"

       stringToCalendar(referenceDate) pipe {
            dateSum(it, 10)
        } pipe {
            dateToString(it)
        } pipe {
            startDate->

            api.getStockQuote(assetName, startDate, referenceDate)
                .map {
                    it.quotes.sortedBy {
                            q->
                        q.referenceDate
                    }.first()

                }
        }



    fun getStockQuote(assetName:String, referenceDate:String){

        //1 - Verificar se existe esse ativo no banco de dados
        Observable.fromCallable { db.stockDao().getStock(assetName) }
            .concatMap {
                   stocks->

                if(stocks.isEmpty()){
                    //Buscar Na api
                    getLastQuote(assetName, referenceDate)
                }else{
                    Observable.just(stocks)
                }

            }
            .map {

            }



        /*

         Observable.fromCallable { postDao.all }
            .concatMap { dbPostList ->

                if(dbPostList.isEmpty()){

                    postApi.getPosts().concatMap {
                        apiPostList->
                        postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }

                }else{
                    Observable.just(dbPostList)
                }

            }
            .subscribeOn(Schedulers.io())

         */


    }


    fun getStockQuotes(assetName:String , startDate:String, endDate:String)=
        api.getStockQuote(assetName,startDate, endDate)


}