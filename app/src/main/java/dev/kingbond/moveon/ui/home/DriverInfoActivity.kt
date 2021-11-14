package dev.kingbond.moveon.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.activity_driver_info.*

class DriverInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_info)

        ivDriverMoreInfo.setOnClickListener {
            if (driveMoreInfoLayout.visibility == View.GONE) {
                driveMoreInfoLayout.visibility = View.VISIBLE
                ivDriverMoreInfo.setImageResource(R.drawable.arrow_up_icon)
            } else {
                driveMoreInfoLayout.visibility = View.GONE
                ivDriverMoreInfo.setImageResource(R.drawable.arrow_down_icon)
            }
        }

        tvDoneBooking.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

