package dev.kingbond.moveon.ui.warehouses.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WareHouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWareHouse(wareHouseEntity: WareHouseEntity)

    @Query("select * from warehouse")
    fun getAllWareHOuse(): LiveData<List<WareHouseEntity>>

}