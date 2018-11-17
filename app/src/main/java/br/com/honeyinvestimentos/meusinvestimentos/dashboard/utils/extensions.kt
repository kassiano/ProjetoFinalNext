package br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils

import android.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.honeyinvestimentos.meusinvestimentos.fp.pipe
import java.text.NumberFormat
import java.util.*


fun Double.formatPercent():String =
    NumberFormat.getPercentInstance() pipe {
        it.maximumFractionDigits = 2
        it.minimumFractionDigits = 2

        it.format(this)
    }

fun Double.formatMoney():String =
    NumberFormat.getCurrencyInstance(Locale("PT","BR")) pipe {
        it.format(this)
    }


fun <T> Spinner.setData(items:List<T>){

    val adapter = ArrayAdapter<T>(this.context, R.layout.simple_spinner_item, items)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    this.adapter = adapter

}
