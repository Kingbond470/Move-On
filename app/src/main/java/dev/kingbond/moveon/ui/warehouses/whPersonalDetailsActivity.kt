package dev.kingbond.moveon.ui.warehouses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.moveon.databinding.ActivityWhPersonalDetailsBinding
import dev.kingbond.moveon.ui.warehouses.SharedPref.MyPreference

class whPersonalDetailsActivity : AppCompatActivity() {

   // private lateinit var binding: ActivityWhPersonalDetailsBinding
    private lateinit var binding:ActivityWhPersonalDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhPersonalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnpersonaldetail.setOnClickListener {
            val intent = Intent(this@whPersonalDetailsActivity,whDetailsActivity::class.java )

            val  name = binding.tvName.text.toString()
           val myPrefernce = MyPreference(this.applicationContext)
            myPrefernce.setName(name)
            startActivity(intent)

        }



    }


}