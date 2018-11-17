package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.EditText
import br.com.honeyinvestimentos.meusinvestimentos.R


class UpdateItemDialog:DialogFragment() {

    companion object {
        const val ASSET_ID = "ASSET_ID"
        const val CURRENT_VALUE = "CURRENT_VALUE"

        fun newInstance(assetId:Int, currentValue:Double): UpdateItemDialog{

            val dialog = UpdateItemDialog()
            val bundle = Bundle()

            bundle.putInt(ASSET_ID, assetId)
            bundle.putDouble(CURRENT_VALUE, currentValue)

            dialog.arguments = bundle
            return dialog
        }
    }

    var assetId:Int? = null
    var currentValue:Double? = null

    lateinit var etCurrentValue: EditText

    interface DialogListenner {
        fun onDialogPositive(assetId:Int, newValue:Double)
    }

    lateinit var listener: DialogListenner


    fun performClick(){

        assetId?.let {
            listener.onDialogPositive(it,
                etCurrentValue.text.toString().toDouble())
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val alertBuilder = AlertDialog.Builder(context)
        val bundle = arguments

        assetId = bundle?.getInt(ASSET_ID)
        currentValue = bundle?.getDouble(CURRENT_VALUE)

        alertBuilder.setTitle("Update Asset Value")
        alertBuilder.setPositiveButton("salvar" ) { dialog, which ->
            performClick()
        }

        alertBuilder.setNegativeButton("cancelar", null)

        val view = LayoutInflater.from(context).inflate(R.layout.update_asset_item, null)

        etCurrentValue = view.findViewById<EditText>(R.id.etCurrentValue)
        etCurrentValue.setText("$currentValue")

        alertBuilder.setView(view)

        return alertBuilder.create()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogListenner
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement DialogListenner")
        }

    }

}