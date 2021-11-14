package dev.kingbond.moveon.ui.packageandmovers.fragMov.housecategory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.navigation.Navigation
import dev.kingbond.moveon.R
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import android.graphics.Paint
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.edit
import dev.kingbond.moveon.ui.packageandmovers.fragMov.itemList.ItemList
import dev.kingbond.moveon.ui.packageandmovers.spref.GlobalV
import dev.kingbond.moveon.ui.packageandmovers.spref.SharedPref
import kotlinx.android.synthetic.main.fragment_house_category.*
import kotlinx.android.synthetic.main.fragment_house_size.*


class HouseCategory : Fragment(), View.OnClickListener {

    val sharedPref = SharedPref()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_house_category, container, false)


        view.findViewById<Button>(R.id.categoryBtn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_houseCategory_to_itemList)

        }

        initi(view)




        return view
    }

    fun initi(view: View) {

        val rk1 = view.findViewById<Button>(R.id.rk1Select)
        val bhk1 = view.findViewById<Button>(R.id.bhk1Select)
        val bhk2 = view.findViewById<Button>(R.id.bhk2Select)
        val bhk3 = view.findViewById<Button>(R.id.bhk3Select)


        rk1.setOnClickListener(this)
        bhk1.setOnClickListener(this)
        bhk2.setOnClickListener(this)
        bhk3.setOnClickListener(this)

    }

    override fun onClick(v: View) {


        when (v.id) {
            R.id.rk1Select -> {
                Toast.makeText(requireActivity(), "1RK Selected", Toast.LENGTH_SHORT).show()

                sharedPref.insertData(requireContext(), "1rk", "1rk selected")
            }

            R.id.bhk1Select -> {
                Toast.makeText(requireActivity(), "1BHK Selected", Toast.LENGTH_SHORT).show()
                sharedPref.insertData(requireContext(), "1bhk", "1bhk selected")
            }
            R.id.bhk2Select -> {
                Toast.makeText(requireActivity(), "2BHK Selected", Toast.LENGTH_SHORT).show()
                sharedPref.insertData(requireContext(), "2bhk", "2bhk selected")
            }
            R.id.bhk3Select -> {
                Toast.makeText(requireActivity(), "3BHK Selected", Toast.LENGTH_SHORT).show()
                sharedPref.insertData(requireContext(), "3bhk", "3bhk selected")
            }
        }


    }


}