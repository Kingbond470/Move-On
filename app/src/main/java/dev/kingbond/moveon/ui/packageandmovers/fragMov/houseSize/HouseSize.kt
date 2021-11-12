package dev.kingbond.moveon.ui.packageandmovers.fragMov.houseSize

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import dev.kingbond.moveon.R
import android.widget.ArrayAdapter

import android.widget.Spinner
import androidx.core.view.get
import dev.kingbond.moveon.ui.packageandmovers.fragMov.housecategory.HouseCategory
import dev.kingbond.moveon.ui.packageandmovers.spref.SharedPref


import kotlinx.android.synthetic.main.fragment_house_size.*
import java.lang.ClassCastException
import java.text.SimpleDateFormat
import java.util.*


class HouseSize : Fragment(), AdapterView.OnItemSelectedListener{

    val sharedPref=SharedPref()

    var country = arrayOf("Mumbai", "Pune", "Banglore", "Delhi", "Other")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_house_size, container, false)



        view.findViewById<Button>(R.id.houseBtn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_houseSize_to_houseCategory)
            senddata(view)
        }

        //MenuDrawer
        val values = arrayOf(
            "Mumbai",
            "Pune",
            "Banglore",
            "Delhi",
            "Other"
        )
        val adapter = ArrayAdapter(this.requireActivity(), android.R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        val spiiner =view.findViewById<Spinner>(R.id.SelectCity)
        spiiner.adapter = adapter





        //datepicker
        val date=view.findViewById<EditText>(R.id.date)
        date.transformIntoDatePicker(requireContext(), "dd/MM/yyyy")
        date.transformIntoDatePicker(requireContext(), "dd/MM/yyyy", Date())


        //DataTransfer
//        val btn=view.findViewById<Button>(R.id.houseBtn)
//
//        btn.setOnClickListener {
//            senddata(view)
//
//        }

        return view
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        Toast.makeText(this.requireContext(),
            country[position],
            Toast.LENGTH_LONG)
            .show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(requireContext(),
            "country[position]",
            Toast.LENGTH_LONG)
            .show()
    }


    fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false
        setTextColor(resources.getColor(R.color.white))

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }


        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }


    }

    fun senddata(view: View){

        sharedPref.insertData(requireContext(),"date",date.text.toString())

        sharedPref.insertData(requireContext(),"origin",MovingFrom.text.toString())

        sharedPref.insertData(requireContext(),"destination",MovingTo.text.toString())



    }



}