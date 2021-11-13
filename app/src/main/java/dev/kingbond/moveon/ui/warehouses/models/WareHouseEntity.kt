package dev.kingbond.moveon.ui.warehouses.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warehouse")
class WareHouseEntity(
                   @ColumnInfo(name = "firstname") var firstname: String,
                   @ColumnInfo(name = "phoneno") var phoneno: String,
                   @ColumnInfo(name = "from") var email: String,
                   @ColumnInfo(name = "dateto") var dateTo :String,
                   @ColumnInfo(name = "datefrom") var dateFrom: String,
                   @ColumnInfo(name = "cityfrom") var cityfrom: String,
                   @ColumnInfo(name = "cityto") var cityto: String
                      )

{   @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = null

}