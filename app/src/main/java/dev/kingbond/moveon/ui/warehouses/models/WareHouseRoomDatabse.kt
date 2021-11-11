package dev.kingbond.moveon.ui.warehouses.models

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [WareHouseEntity:: class], version = 2)
abstract class WareHouseRoomDatabse:RoomDatabase() {

    abstract fun WareHouseDao(): WareHouseDao

    companion object{
        private var INSTANCE:WareHouseRoomDatabse?= null

        fun getDatabaseObject(context: Context):WareHouseRoomDatabse{
            if(INSTANCE == null){
                val builder  = Room.databaseBuilder(
                    context.applicationContext,
                    WareHouseRoomDatabse::class.java,
                    "warehouse_db"
                )

                builder.fallbackToDestructiveMigration()

                INSTANCE = builder.build()
                return INSTANCE!!
            }else{
                return INSTANCE!!
            }
        }
    }

}