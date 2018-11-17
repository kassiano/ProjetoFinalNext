package br.com.honeyinvestimentos.meusinvestimentos

import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.dateSum
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.dateToLong
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.dateToString
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.stringToCalendar
import org.junit.Assert
import org.junit.Test
import java.util.*

class DateUtilUnitTest{

    @Test
    fun date_to_long_is_correct(){

        val cal = Calendar.getInstance()
        cal.set(2017,10,14)

        val result = dateToLong(cal)

        val expected = 20171114L

        Assert.assertEquals(expected, result)

    }


    @Test
    fun date_to_string_is_correct(){

        val cal = Calendar.getInstance()
        cal.set(2017,10,14)

        val result = dateToString(cal)

        val expected = "2017-11-14"

        Assert.assertEquals(expected, result)
    }

    @Test
    fun date_sum_is_correct(){

        val cal = Calendar.getInstance()
        cal.set(2017,10,14)

        val calResult = dateSum(cal, 6)

        val result = calResult.get(Calendar.DAY_OF_MONTH)

        val expected = 20

        Assert.assertEquals(expected, result)
    }

    @Test
    fun date_string_to_calendar_is_correct(){

        val input = "2018-11-10"

        val expectedCal = Calendar.getInstance()
        expectedCal.set(2018,10,10)

        val expectedYear = expectedCal.get(Calendar.YEAR)
        val expectedMonth = expectedCal.get(Calendar.MONTH)
        val expectedDay = expectedCal.get(Calendar.DAY_OF_MONTH)

        val calResult = stringToCalendar(input)
        val resultYear = expectedCal.get(Calendar.YEAR)
        val resultMonth = expectedCal.get(Calendar.MONTH)
        val resultDay = expectedCal.get(Calendar.DAY_OF_MONTH)

        Assert.assertEquals(expectedYear, resultYear)
        Assert.assertEquals(expectedMonth, resultMonth)
        Assert.assertEquals(expectedDay, resultDay)

    }


}