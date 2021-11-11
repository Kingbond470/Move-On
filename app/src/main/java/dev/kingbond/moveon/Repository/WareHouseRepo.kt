package dev.kingbond.moveon.Repository

import androidx.lifecycle.LiveData
import dev.kingbond.moveon.ui.warehouses.models.WareHouseDao
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WareHouseRepo(private val wareHouseDao: WareHouseDao) {
    fun addWareHouseToRoom(wareHouseEntity: WareHouseEntity) {

        CoroutineScope(Dispatchers.IO).launch {
            wareHouseDao.addWareHouse(wareHouseEntity)

        }
    }


    fun getAllWareHouse(): LiveData<List<WareHouseEntity>> {

        return wareHouseDao.getAllWareHOuse()

    }

}