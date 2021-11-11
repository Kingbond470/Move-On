package dev.kingbond.moveon.ui.warehouses

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import dev.kingbond.moveon.R
import dev.kingbond.moveon.databinding.ActivityWhDetailsBinding
import kotlinx.android.synthetic.main.activity_wh_details.*
import java.text.SimpleDateFormat
import java.util.*

class whDetailsActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    private var calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var pickDate: DatePickerDialog.OnDateSetListener


    private lateinit var bindingg: ActivityWhDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingg = ActivityWhDetailsBinding.inflate(layoutInflater)
        setContentView(bindingg.root)
        bindingg.btndetails.setOnClickListener {
            val intent = Intent(applicationContext, whTypeWareHouse::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        PickDate()
        val state = resources.getStringArray(R.array.city_list)
        val arrayAdapter = ArrayAdapter(this,R.layout.items_list,state)
        bindingg.dropItem.setAdapter(arrayAdapter)



        val city = resources.getStringArray(R.array.type_warehouse)
        val cityadapter = ArrayAdapter(this,R.layout.items_list,city)
        bindingg.dropItem2.setAdapter(cityadapter)

        tvFrom.setOnClickListener {
            val mYear: Int = calendar.get(Calendar.YEAR)
            val mMonth: Int = calendar.get(Calendar.MONTH)
            val mDay: Int = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this,pickDate,mYear,mMonth,mDay).show()
            tvFrom.text = dateFormat.format(calendar.time).toString()

        }



    }

    private fun PickDate(){
        pickDate = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            tvFrom.text = dateFormat.format(calendar.time).toString()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent?.getItemAtPosition(position)!!.equals("Agra")){

        }else{
            val item = parent?.getItemAtPosition(position).toString()
            when (item) {
                "Agra" -> launchFragment()
                "Ahmedabad" -> print("x == 2")
                else -> { // Note the block
                    print("x is neither 1 nor 2")
                }
            }
            when(item){
                "Agra" -> Toast.makeText(this, item+"Selected", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    private fun launchFragment() {
        val fragmentManager = supportFragmentManager
        val fragmenttrans =fragmentManager.beginTransaction()
        val usrDetailFragment = whHouseHoldFragment()
        fragmenttrans.add(R.id.container, usrDetailFragment, "userfrag").addToBackStack("addTOBackstach").commit()
    }

}



