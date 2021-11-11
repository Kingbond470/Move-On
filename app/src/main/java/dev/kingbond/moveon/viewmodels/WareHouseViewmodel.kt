package dev.kingbond.moveon.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.kingbond.moveon.Repository.WareHouseRepo
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity

class WareHouseViewmodel(val wareHouseRepo: WareHouseRepo):ViewModel() {

    fun addWareHouse(wareHouseEntity: WareHouseEntity) {
        wareHouseRepo.addWareHouseToRoom(wareHouseEntity)
    }

    fun getAllWareHouse(): LiveData<List<WareHouseEntity>> {
        return wareHouseRepo.getAllWareHouse()
    }
}