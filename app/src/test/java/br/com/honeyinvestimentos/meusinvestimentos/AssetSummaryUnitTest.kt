package br.com.honeyinvestimentos.meusinvestimentos

import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.calcInvestiment
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.calcPostition
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.calcYield
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AssetUtilsUnitTest {


    @Test
    fun investment_is_correct(){

        val initialPrice = 19.80
        val quantity = 1000
        val result = calcInvestiment(initialPrice, quantity)

        val expected :Double = 19800.0
        assertEquals( expected , result, 0.0 )
    }


    @Test
    fun position_is_correct(){

        val currentPrice = 25.35
        val quantity = 1000
        val result = calcPostition(currentPrice, quantity)

        val expected :Double = 25350.0
        assertEquals( expected , result, 0.0 )
    }


    @Test
    fun yield_is_correct(){


        val currentPrice = 25.35
        val initialPrice = 19.80
        val result = calcYield(currentPrice, initialPrice)

        val expected :Double = 0.2803030303030303
        assertEquals( expected , result, 0.0 )
    }
}
