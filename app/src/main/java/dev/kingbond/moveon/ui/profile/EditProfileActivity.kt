package dev.kingbond.moveon.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private var sex: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)


        //back button
        cdProfileBack.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnSave.setOnClickListener {
            Toast.makeText(baseContext, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        radioMale.setOnClickListener {
            ivProfileImage.setImageResource(R.drawable.man)
        }

        radioFemale.setOnClickListener {
            ivProfileImage.setImageResource(R.drawable.woman)
        }

        radioOthers.setOnClickListener {
            ivProfileImage.setImageResource(R.drawable.other)
        }

    }

}
