package dev.kingbond.moveon.ui.warehouses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.moveon.R
import dev.kingbond.moveon.databinding.ActivityWhEstimatedCostBinding
import kotlinx.android.synthetic.main.activity_wh_estimated_cost.*

class whEstimatedCostActivity : AppCompatActivity() {
    private lateinit var binding :ActivityWhEstimatedCostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhEstimatedCostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequestCallback.setOnClickListener {
            val intent = Intent(applicationContext, RequestCallbackActivity::class.java)
            startActivity(intent)
        }
    }
}