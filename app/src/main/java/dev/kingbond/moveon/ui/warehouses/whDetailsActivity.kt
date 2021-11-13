package dev.kingbond.moveon.ui.warehouses

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import dev.kingbond.moveon.R
import dev.kingbond.moveon.databinding.ActivityWhDetailsBinding
import kotlinx.android.synthetic.main.activity_wh_details.*
import kotlinx.android.synthetic.main.activity_wh_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class whDetailsActivity : AppCompatActivity() {

    //AIzaSyC1e0m1TWu-JpYPqyQWF_iwFrD8BcSophI  map api keyy
    private var calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var pickDateFrom: DatePickerDialog.OnDateSetListener
    private lateinit var pickDatetTo: DatePickerDialog.OnDateSetListener


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
        PickDateFrom()
        PickDateTo()
        val state = resources.getStringArray(R.array.state_list)
        val arrayAdapter = ArrayAdapter(this, R.layout.items_list, state)
        bindingg.dropItem.setAdapter(arrayAdapter)


        val city = resources.getStringArray(R.array.city_list)
        val cityadapter = ArrayAdapter(this, R.layout.items_list, city)
        bindingg.dropItem2.setAdapter(cityadapter)



        tvFrom.setOnClickListener {
            val mYear: Int = calendar.get(Calendar.YEAR)
            val mMonth: Int = calendar.get(Calendar.MONTH)
            val mDay: Int = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, pickDateFrom, mYear, mMonth, mDay).show()
            tvFrom.text = dateFormat.format(calendar.time).toString()

        }

        tcvTo.setOnClickListener {
            val tYear: Int = calendar.get(Calendar.YEAR)
            val tMonth: Int = calendar.get(Calendar.MONTH)
            val tDay: Int = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, pickDatetTo, tYear, tMonth, tDay).show()
            tcvTo.text = dateFormat.format(calendar.time).toString()

        }


    }

    private fun PickDateFrom() {
        pickDateFrom = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tvFrom.text = dateFormat.format(calendar.time).toString()
        }
    }
    private fun PickDateTo() {
        pickDatetTo = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tcvTo.text = dateFormat.format(calendar.time).toString()
        }
    }


}



