package dev.kingbond.moveon.ui.coupons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.activity_coupons.*

class CouponsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupons)

        // return to MainActivity
        backToHome.setOnClickListener {
            val intent= Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}