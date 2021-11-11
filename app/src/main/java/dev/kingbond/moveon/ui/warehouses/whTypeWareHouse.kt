package dev.kingbond.moveon.ui.warehouses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import dev.kingbond.moveon.R
import dev.kingbond.moveon.databinding.ActivityWhTypeWareHouseBinding

class whTypeWareHouse : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var bindingg: ActivityWhTypeWareHouseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingg = ActivityWhTypeWareHouseBinding.inflate(layoutInflater)
        setContentView(bindingg.root)
        bindingg.btndetails.setOnClickListener {
            val intent = Intent(applicationContext, whEstimatedCostActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val type = resources.getStringArray(R.array.type)
        val arrayAdapter3 = ArrayAdapter(this, R.layout.items_list, type)
        bindingg.dropItem3.setAdapter(arrayAdapter3)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent!!.getItemAtPosition(position)!!.equals("Household")) {
            val intent = Intent(this, whEstimatedCostActivity::class.java)
            startActivity(intent)
            launchFragment()
        }
            val item = parent?.getItemAtPosition(position).toString()
            when (item) {
                "Household" ->{
                    launchFragment()
                }
                "Vehiles" ->{
                    launchFragment()
                }

                else -> { // Note the block

                }
            }
            when (item) {
                "Vehiles" -> Toast.makeText(this, item + "Selected", Toast.LENGTH_SHORT).show()
            }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun launchFragment() {
        val fragmentManager = supportFragmentManager
        val fragmenttrans = fragmentManager.beginTransaction()
        val houseHoldFragment = whHouseHoldFragment()
        fragmenttrans.add(R.id.llContainer, houseHoldFragment, "userfrag").commit()
    }

}
