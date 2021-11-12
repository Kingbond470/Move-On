package dev.kingbond.moveon.ui.packageandmovers.fragMov.itemList

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.navigation.Navigation
import dev.kingbond.moveon.R
import dev.kingbond.moveon.ui.packageandmovers.fragMov.cost.CoastPage
import dev.kingbond.moveon.ui.packageandmovers.spref.GlobalV
import dev.kingbond.moveon.ui.packageandmovers.spref.SharedPref
import kotlinx.android.synthetic.main.fragment_house_size.*
import kotlinx.android.synthetic.main.fragment_item_list.*


class ItemList : Fragment(), View.OnClickListener {

    val sharedPref = SharedPref()

    var sum = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        view.findViewById<Button>(R.id.itemBtn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_itemList_to_coastPage)
            sharedPref.insertIntData(requireContext(), "sum", sum)

        }
        setData(view)
//        HouseCat_data(view)


        return view
    }

    fun setData(v: View) {


        val mat = v.findViewById<CheckBox>(R.id.Matress)
        val Almirah = v.findViewById<CheckBox>(R.id.Almirah)
        val chair = v.findViewById<CheckBox>(R.id.Chair)
        val Sofa = v.findViewById<CheckBox>(R.id.Sofa)
        val Table = v.findViewById<CheckBox>(R.id.Table)
        val Purifier = v.findViewById<CheckBox>(R.id.Purifier)
        val Shoe = v.findViewById<CheckBox>(R.id.Shoe)
        val Fridge = v.findViewById<CheckBox>(R.id.Fridge)
        val TV = v.findViewById<CheckBox>(R.id.WashingMachine)
        val GasStove = v.findViewById<CheckBox>(R.id.GasStove)
        val Cylinder = v.findViewById<CheckBox>(R.id.Cyclinder)
        val Bed = v.findViewById<CheckBox>(R.id.bed)
        val Specific = v.findViewById<CheckBox>(R.id.specific)

        mat.setOnClickListener(this)
        Almirah.setOnClickListener(this)
        chair.setOnClickListener(this)
        Sofa.setOnClickListener(this)
        Table.setOnClickListener(this)
        Purifier.setOnClickListener(this)
        Shoe.setOnClickListener(this)
        Fridge.setOnClickListener(this)
        TV.setOnClickListener(this)
        GasStove.setOnClickListener(this)
        Cylinder.setOnClickListener(this)
        Bed.setOnClickListener(this)
        Specific.setOnClickListener(this)


    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.Matress -> {
                if (Matress.isChecked) {
                    Toast.makeText(requireActivity(), "Maitress", Toast.LENGTH_SHORT).show()

//                    sharedPref.insertIntData(requireContext(), "Maitress", sum+100)
                    sum += 100
                }
            }
            R.id.Almirah -> {
                if (Almirah.isChecked) {
                    Toast.makeText(requireActivity(), "Almirah", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Almirah", sum+200)
                    sum += 200
                }
            }
            R.id.Chair -> {
                if (Chair.isChecked) {
                    Toast.makeText(requireActivity(), "Chair", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Chair", sum + 300)
                    sum += 300
                }
            }
            R.id.Sofa -> {
                if (Sofa.isChecked) {
                    Toast.makeText(requireActivity(), "Sofa", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Sofa", sum+400)
                    sum += 400
                }
            }
            R.id.Table -> {
                if (Table.isChecked) {
                    Toast.makeText(requireActivity(), "Table", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Table", sum+500)
                    sum += 500
                }
            }
            R.id.Purifier -> {
                if (Purifier.isChecked) {
                    Toast.makeText(requireActivity(), "Table", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Table", sum+600)
                    sum += 600
                }
            }
            R.id.Shoe -> {
                if (Shoe.isChecked) {
                    Toast.makeText(requireActivity(), "Shoe", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Shoe", sum+700)
                    sum += 700
                }
            }

            R.id.Fridge -> {
                if (Fridge.isChecked) {
                    Toast.makeText(requireActivity(), "Fridge", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Fridge", sum+800)
                    sum += 800
                }
            }

            R.id.WashingMachine -> {
                if (WashingMachine.isChecked) {
                    Toast.makeText(requireActivity(), "TV", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "washingMachine", sum+900)
                    sum += 900
                }
            }

            R.id.GasStove -> {
                if (GasStove.isChecked) {
                    Toast.makeText(requireActivity(), "GasStove", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "GasStove", sum+500)
                    sum += 500
                }
            }

            R.id.Cyclinder -> {
                if (Cyclinder.isChecked) {
                    Toast.makeText(requireActivity(), "Cyclinder", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Cyclinder", sum+500)
                    sum += 500
                }
            }

            R.id.bed -> {
                if (bed.isChecked) {
                    Toast.makeText(requireActivity(), "Bed", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "Bed", sum+500)
                    sum += 300
                }
            }

            R.id.specific -> {
                if (specific.isChecked) {
                    Toast.makeText(requireActivity(), "specific", Toast.LENGTH_SHORT).show()
//                    sharedPref.insertIntData(requireContext(), "specific", sum+1000)
                    sum += 1000
                }
            }


        }
    }


}












