package dev.kingbond.moveon.ui.packageandmovers.fragMov.cost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import dev.kingbond.moveon.R
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import android.widget.Switch
import dev.kingbond.moveon.ui.packageandmovers.spref.GlobalV
import dev.kingbond.moveon.ui.packageandmovers.spref.SharedPref



class CoastPage : Fragment() {

    val sharedPref= SharedPref()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_coast_page, container, false)


        view.findViewById<Button>(R.id.coastBtn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_coastPage_to_confirmPage)
        }


        houseSizeData(view)
//        setFinal1(view)



        return view
    }

    fun houseSizeData(view:View){




        val cost=view.findViewById<TextView>(R.id.EstCost)

        val date=view.findViewById<TextView>(R.id.Tdate)

        var value_1 = sharedPref.retriveData(requireContext(),"date")

        var Tcost = sharedPref.retriveIntData(requireContext(),"sum")


        cost.text = "₹${Tcost.toString()}.00"



        date.text = value_1.toString()
    }

//    fun setFinal1(view:View){

        ////unable to implement

//        val type=view.findViewById<TextView>(R.id.Thouse_type)
//
//
//
//        val Cat1=sharedPref.retriveData(requireContext(),"1rk")
//
//        val Cat2=sharedPref.retriveData(requireContext(),"1bhk")
//
//        val Cat3=sharedPref.retriveData(requireContext(),"2bhk")
//
//        val Cat4=sharedPref.retriveData(requireContext(),"3bhk")




//
//        when {
//            Cat1!=null -> {
//                Toast.makeText(requireContext(), Cat1, Toast.LENGTH_SHORT).show()
//                type.text = Cat1
//            }
//            Cat2!=null -> {
//                Toast.makeText(requireContext(), Cat2, Toast.LENGTH_SHORT).show()
//                type.text = Cat2
//            }
//            Cat3!=null -> {
//                Toast.makeText(requireContext(), Cat3, Toast.LENGTH_SHORT).show()
//                type.text = Cat3
//            }
//            else -> {
//                type.text = Cat4
//            }
//        }





//    }




}