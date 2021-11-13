package dev.kingbond.moveon.ui.warehouses

import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity

interface ItemClickListener {
    fun onItremClick( entity: WareHouseEntity,  position :Int)
}