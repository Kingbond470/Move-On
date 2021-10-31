package dev.kingbond.moveon.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.moveon.R

class MapActivity : AppCompatActivity() {

    val TAG: String = "Map Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
    }
}