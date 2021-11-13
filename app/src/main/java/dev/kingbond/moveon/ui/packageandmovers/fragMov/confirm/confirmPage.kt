package dev.kingbond.moveon.ui.packageandmovers.fragMov.confirm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.kingbond.moveon.R
import dev.kingbond.moveon.ui.warehouses.whOrdersActivity


class confirmPage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_confirm_page, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val i =Intent(requireContext(),whOrdersActivity::class.java)
        startActivity(i)
    }


}