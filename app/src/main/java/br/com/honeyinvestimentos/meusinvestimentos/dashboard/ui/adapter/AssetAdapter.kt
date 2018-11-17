package br.com.honeyinvestimentos.meusinvestimentos.dashboard.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.honeyinvestimentos.meusinvestimentos.R
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.domain.AssetSummary
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.formatMoney
import br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils.formatPercent

class AssetAdapter(context: Context,
                   val onEditClick:(AssetSummary)->Unit,
                   val onLongClick:(AssetSummary)->Boolean
                ): Adapter<AssetAdapter.AssetViewHolder>() {

    val inflater = LayoutInflater.from(context)

    var assets : List<AssetSummary> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val itemView = inflater.inflate(R.layout.asset_view_item, parent, false)

        return AssetViewHolder(itemView)
    }

    override fun getItemCount()= assets.size

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {

        holder.nameView.text = assets[position].name
        holder.currentPriceView.text = assets[position].currentPrice.formatMoney()
        holder.investimentView.text = assets[position].investiment.formatMoney()
        holder.quantityView.text = assets[position].quantity.toString()
        holder.positionView.text = assets[position].postition.formatMoney()
        holder.tvYieldView.text = assets[position].yieldValue.formatPercent()

        holder.btEditView.setOnClickListener {
            onEditClick(assets[position])
        }

        holder.itemView.setOnLongClickListener {
            onLongClick(assets[position])
        }
    }


     class AssetViewHolder(itemView: View) : ViewHolder(itemView) {

         val nameView: TextView = itemView.findViewById(R.id.tvAssetName)
         val currentPriceView = itemView.findViewById<TextView>(R.id.tvCurrentPrice)
         val investimentView = itemView.findViewById<TextView>(R.id.tvInvestment)
         val quantityView = itemView.findViewById<TextView>(R.id.tvQuantity)
         val positionView = itemView.findViewById<TextView>(R.id.tvPosition)
         val tvYieldView = itemView.findViewById<TextView>(R.id.tvYield)
         val btEditView = itemView.findViewById<ImageView>(R.id.btEdit)

    }

}