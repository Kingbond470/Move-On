package dev.kingbond.moveon.ui.warehouses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.moveon.R
import dev.kingbond.moveon.databinding.ActivityRequestCallbackBinding
import dev.kingbond.moveon.databinding.ActivityWhEstimatedCostBinding

class RequestCallbackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestCallbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestCallbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDone.setOnClickListener {
            val intent = Intent(applicationContext, whOrdersActivity::class.java)
            startActivity(intent)
        }

    }
}