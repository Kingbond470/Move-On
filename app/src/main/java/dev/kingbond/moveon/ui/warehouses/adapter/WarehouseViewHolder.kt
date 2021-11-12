package dev.kingbond.moveon.ui.warehouses.adapter

import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.moveon.databinding.ItemLayoutWarehouseBinding
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity

class WarehouseViewHolder(val binding : ItemLayoutWarehouseBinding):RecyclerView.ViewHolder(binding.root) {

    fun SetData(entity: WareHouseEntity){

binding.apply {
    tvName.text = entity.firstname
    tvEmail.text = entity.email
    tvPhine.text = entity.phoneno
    tvDatefrom.text = entity.dateFrom
    tvDateTo.text = entity.dateTo
    tvCityFrom.text = entity.cityfrom
    tvCityTo.text = entity.cityto

}

    }
}