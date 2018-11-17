package br.com.honeyinvestimentos.meusinvestimentos.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.honeyinvestimentos.meusinvestimentos.data.dao.AssetDao
import br.com.honeyinvestimentos.meusinvestimentos.data.dao.CategoryDao
import br.com.honeyinvestimentos.meusinvestimentos.data.dao.ProductDao
import br.com.honeyinvestimentos.meusinvestimentos.data.dao.StockDao
import br.com.honeyinvestimentos.meusinvestimentos.data.entities.*
import org.jetbrains.anko.doAsync


@Database( entities = [Category::class, Product::class, Asset::class, Stock::class, StockQuotes::class] ,
           version = 1 ,exportSchema = true)
abstract class InvestmentsDatabase: RoomDatabase(){
    abstract fun assetDao():AssetDao
    abstract fun categoryDao():CategoryDao
    abstract fun productDao():ProductDao
    abstract fun stockDao(): StockDao
}


private var databaseOption : InvestmentsDatabase? = null

private val roomDatabaseCallBack = object: RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        doAsync {

            room.apply {

                //config Renda Fixa

                val idRendaFixa :Long =  categoryDao().insert(Category( title = "Renda Fixa" ))

                productDao().insert(Product(0, "CERTIFICADO DE RECEBÍVEIS IMOBILIÁRIOS", "CRI",1, idRendaFixa ))
                productDao().insert(Product(0, "CERTIFICADO DE DEPÓSITO BANCÁRIO", "CDB",1, idRendaFixa ))
                productDao().insert(Product(0, "CERTIFICADO DE RECEBÍVEIS DO AGRONEGÓCIO", "CRA",1, idRendaFixa ))
                productDao().insert(Product(0, "DEBÊNTURE", "DEB",1, idRendaFixa ))
                productDao().insert(Product(0, "LETRA DE CÂMBIO", "LC", 1,idRendaFixa ))
                productDao().insert(Product(0, "LETRA DE CRÉDITO DO AGRONEGÓCIO", "LCA",1, idRendaFixa ))
                productDao().insert(Product(0, "LETRA DE CRÉDITO IMOBILIÁRIO", "LCI",1, idRendaFixa ))


                val idFund = categoryDao().insert(Category( title = "Fundos de investimento" ))
                productDao().insert(Product(0, "Multimercados", "",3, idFund ))
                productDao().insert(Product(0, "Renda Fixa", "", 1,idFund ))
                productDao().insert(Product(0, "Ações", "",4, idFund ))
                productDao().insert(Product(0, "Cambial", "",2, idFund ))
                productDao().insert(Product(0, "Internacional", "",2, idFund ))


                val idCoe = categoryDao().insert(Category( title = "COE" ))
                productDao().insert(Product(0, "Autocallable", "",2, idCoe ))
                productDao().insert(Product(0, "Participação", "",2, idCoe ))


                val idRendaVariavel = categoryDao().insert(Category( title = "Renda variável" ))
                productDao().insert(Product(0, "Ações", "",4, idRendaVariavel ))
                productDao().insert(Product(0, "Opções", "",5, idRendaVariavel ))

            }

        }

    }
}


fun initDatabase(context: Context){
    if(databaseOption == null){

        databaseOption = Room.databaseBuilder(
            context.applicationContext,
            InvestmentsDatabase::class.java,
            "investiments_database"
        )
        .fallbackToDestructiveMigration()
        .addCallback(roomDatabaseCallBack)
        .build()
    }
}

val room: InvestmentsDatabase
    get() = databaseOption ?: throw Exception("You need to initialize the database.")