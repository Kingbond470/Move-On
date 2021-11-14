package dev.kingbond.moveon.ui.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import dev.kingbond.moveon.R
import dev.kingbond.moveon.ui.packageandmovers.spref.SharedPref
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.fragment_house_size.*

class PaymentA : AppCompatActivity() {

    val sharedPref= SharedPref()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)


        btnPayment.setOnClickListener {
            parcelData()
        }

    }

    private fun parcelData() {

        BWallet.setOnClickListener {
            sharedPref.insertData(this, "MoveOn", "MoveOnWallet")
        }
        BCash.setOnClickListener {
            sharedPref.insertData(this, "Cash", "Cash")
        }

        Bpaytm.setOnClickListener {
            sharedPref.insertData(this, "paytm", "Paytm")
        }

        Bgoogle.setOnClickListener {
            sharedPref.insertData(this, "google", "Google Pay")
            val i=Intent(this,googlepay::class.java)
            startActivity(i)
        }


    }
}