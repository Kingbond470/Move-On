package dev.kingbond.moveon.ui.warehouses.adapter

import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.moveon.databinding.ItemLayoutWarehouseBinding
import dev.kingbond.moveon.ui.warehouses.ItemClickListener
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity

class WarehouseViewHolder(
    val binding: ItemLayoutWarehouseBinding,
    val itemClickListener: ItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun SetData(entity: WareHouseEntity) {

        binding.apply {
             WareHouseCost.text = entity.firstname.toString()
            tvEmail.text = entity.email
            //  tvPhine.text = entity.phoneno
            tvdates.text = entity.dateFrom
//    tvDateTo.text = entity.dateTo
            tvLocation.text = entity.cityfrom
            tvdropLocation.text = entity.cityto
            tvViewMore.setOnClickListener {
                itemClickListener.onItremClick(entity, adapterPosition)

            }


        }

    }
}