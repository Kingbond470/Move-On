package dev.kingbond.moveon.ui.warehouses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dev.kingbond.moveon.Repository.WareHouseRepo
import dev.kingbond.moveon.databinding.ActivityWhOrdersBinding
import dev.kingbond.moveon.ui.warehouses.models.WareHouseDao
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity
import dev.kingbond.moveon.ui.warehouses.models.WareHouseRoomDatabse
import dev.kingbond.moveon.viewmodels.WareHouseViewmodel
import dev.kingbond.moveon.viewmodels.WareHouseViewmodelFactory

class whOrdersActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWhOrdersBinding

    private lateinit var roomDb : WareHouseRoomDatabse
    private lateinit var wareHouseDao: WareHouseDao
    private lateinit var viewmodel: WareHouseViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomDb = WareHouseRoomDatabse.getDatabaseObject(this)
        wareHouseDao = roomDb.WareHouseDao()


        val repo = WareHouseRepo(wareHouseDao)
        val viewModelFactory = WareHouseViewmodelFactory(repo)

        //object of view model
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(WareHouseViewmodel::class.java)


        binding.btnAddWareHouse.setOnClickListener {
            val ware = WareHouseEntity("MIntu Kumar ", "99287979",
                "sainimintu34@gmail.com","28/0/2021","30/03/2021", "jaipur", "Delhi"
                    )
            viewmodel.addWareHouse(ware)

        }
    }
}