package dev.kingbond.moveon.ui.warehouses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.moveon.databinding.ItemLayoutWarehouseBinding
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity

class WareHouseAdapter(val list: ArrayList<WareHouseEntity>):
    RecyclerView.Adapter<WarehouseViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarehouseViewHolder {
        val binding =ItemLayoutWarehouseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WarehouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WarehouseViewHolder, position: Int) {
        val entity : WareHouseEntity = list[position]
        holder.SetData(entity)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}