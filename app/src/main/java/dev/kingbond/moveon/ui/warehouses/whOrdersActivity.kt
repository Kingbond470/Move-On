package dev.kingbond.moveon.ui.warehouses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.moveon.Repository.WareHouseRepo
import dev.kingbond.moveon.databinding.ActivityWhOrdersBinding
import dev.kingbond.moveon.ui.warehouses.SharedPref.MyPreference
import dev.kingbond.moveon.ui.warehouses.adapter.WareHouseAdapter
import dev.kingbond.moveon.ui.warehouses.models.WareHouseDao
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity
import dev.kingbond.moveon.ui.warehouses.models.WareHouseRoomDatabse
import dev.kingbond.moveon.viewmodels.WareHouseViewmodel
import dev.kingbond.moveon.viewmodels.WareHouseViewmodelFactory

class whOrdersActivity : AppCompatActivity(), ItemClickListener {

    //Binding
    private lateinit var binding : ActivityWhOrdersBinding
    //Mvvm
    private lateinit var roomDb : WareHouseRoomDatabse
    private lateinit var wareHouseDao: WareHouseDao
    private lateinit var viewmodel: WareHouseViewmodel
    private lateinit var wareuseAdapter: WareHouseAdapter
    private  val warehouselist = mutableListOf<WareHouseEntity>()
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
        val myPreference = MyPreference(context = applicationContext)
        var name = myPreference.getName()!!
        val ware = WareHouseEntity(name,"987q93279",
            "sainimintu34@gmail.com","28/0/2021","30/03/2021", "jaipur", "Delhi"
        )
        viewmodel.addWareHouse(ware)

        wareuseAdapter = WareHouseAdapter(warehouselist as ArrayList<WareHouseEntity>, this)
        binding.rvRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.rvRecyclerView.adapter = wareuseAdapter

        viewmodel.getAllWareHouse().observe(this, Observer {
            warehouselist.clear()
            warehouselist.addAll(it)
            wareuseAdapter.notifyDataSetChanged()
        })




    }

    override fun onItremClick(entity: WareHouseEntity, position: Int) {
        val intent = Intent(applicationContext,whOrderDetails::class.java)
        startActivity(intent)
    }
}