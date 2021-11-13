package dev.kingbond.moveon.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.kingbond.moveon.R

class EmailLoginActivity : AppCompatActivity(), FinishActivityClickListner{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

    }


    // to finish the activity from fragment
    override fun onClick() {
        finish()
    }

}