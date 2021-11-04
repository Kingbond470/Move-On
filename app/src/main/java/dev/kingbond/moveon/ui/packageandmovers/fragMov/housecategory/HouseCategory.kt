package dev.kingbond.moveon.ui.packageandmovers.fragMov.housecategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import dev.kingbond.moveon.R



class HouseCategory : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_house_category, container, false)

        view.findViewById<Button>(R.id.categoryBtn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_houseCategory_to_itemList)
        }

        return view
    }


}