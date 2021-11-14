package dev.kingbond.moveon.ui.warehouses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import dev.kingbond.moveon.Repository.WareHouseRepo
import dev.kingbond.moveon.databinding.ActivityWhOrdersBinding
import dev.kingbond.moveon.ui.home.recyclerview.Vehicles
import dev.kingbond.moveon.ui.packageandmovers.spref.SharedPref
import dev.kingbond.moveon.ui.payment.PaymentA
import dev.kingbond.moveon.ui.payment.googlepay
import dev.kingbond.moveon.ui.warehouses.SharedPref.MyPreference
import dev.kingbond.moveon.ui.warehouses.adapter.WareHouseAdapter
import dev.kingbond.moveon.ui.warehouses.models.WareHouseDao
import dev.kingbond.moveon.ui.warehouses.models.WareHouseEntity
import dev.kingbond.moveon.ui.warehouses.models.WareHouseRoomDatabse
import dev.kingbond.moveon.viewmodels.WareHouseViewmodel
import dev.kingbond.moveon.viewmodels.WareHouseViewmodelFactory
import kotlinx.android.synthetic.main.activity_wh_orders.*
import kotlinx.android.synthetic.main.payment_methods_layout.*

class whOrdersActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var bottomSheetDialogForPaymentMethods: BottomSheetDialog

    //Binding
    private lateinit var binding : ActivityWhOrdersBinding
    //Mvvm
    private lateinit var roomDb : WareHouseRoomDatabse

    //shared
    val sharedPref= SharedPref()

    private lateinit var wareHouseDao: WareHouseDao
    private lateinit var viewmodel: WareHouseViewmodel
    private lateinit var wareuseAdapter: WareHouseAdapter
    private  val warehouselist = mutableListOf<WareHouseEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val moving_from=sharedPref.retriveData(this,"origin")
        val moving_to=sharedPref.retriveData(this,"destination")
        val House_Date=sharedPref.retriveData(this,"date")
        val House_Sum=sharedPref.retriveIntData(this,"sum")


        roomDb = WareHouseRoomDatabse.getDatabaseObject(this)
        wareHouseDao = roomDb.WareHouseDao()
        val repo = WareHouseRepo(wareHouseDao)
        val viewModelFactory = WareHouseViewmodelFactory(repo)
        //object of view model
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(WareHouseViewmodel::class.java)
        val myPreference = MyPreference(context = applicationContext)
        var name = myPreference.getName()!!
        val ware = WareHouseEntity("₹${House_Sum.toString()}","987493279",
            "sainimintu34@gmail.com",House_Date.toString(),"30/03/2021", moving_from.toString(), moving_to.toString()
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


        binding.IvBackBookingDetails.setOnClickListener {
            val i =Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }


    }

    override fun onItremClick(entity: WareHouseEntity, position: Int) {
//        val intent = Intent(applicationContext,PaymentA::class.java)
//        startActivity(intent)
        bottomSheetForPaymentMethods()

    }

    private fun bottomSheetForPaymentMethods() {
        bottomSheetDialogForPaymentMethods = BottomSheetDialog(this@whOrdersActivity)
        val viewPaymentMethods = layoutInflater.inflate(R.layout.payment_methods_layout, null)
        bottomSheetDialogForPaymentMethods.setContentView(viewPaymentMethods)
        bottomSheetDialogForPaymentMethods.rb_google_pay.isChecked = true
        bottomSheetDialogForPaymentMethods.show()

        bottomSheetDialogForPaymentMethods.btnConfirmPaymentMethods.setOnClickListener {
            bottomSheetDialogForPaymentMethods.cancel()
            val i=Intent(this,googlepay::class.java)
            startActivity(i)

        }

    }
}