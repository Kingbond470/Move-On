package dev.kingbond.moveon.ui.warehouses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.kingbond.moveon.R

class whHouseHoldFragment : Fragment() {
//private  lateinit var binding: whHouseHold

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

}

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}

