package dev.kingbond.moveon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.liquidswipedemo.CustomFragmentPagerAdapter
import com.example.liquidswipedemo.CustomPagerAdapter
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider
import dev.kingbond.moveon.ui.login.Login
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboadingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        viewpager.adapter = CustomFragmentPagerAdapter(supportFragmentManager)


        val liquidSwipeClipPathProviders = Array(titleArray.count()) {
            LiquidSwipeClipPathProvider()
        }

        viewpager.adapter = CustomPagerAdapter(this, liquidSwipeClipPathProviders)
        viewpager.setOnTouchListener { _, event ->
            val waveCenterY = event.y
            liquidSwipeClipPathProviders.map {
                it.waveCenterY = waveCenterY
            }
            false
        }

        ImgGif.setOnClickListener {
            val i =Intent(this,Login::class.java)
            startActivity(i)
        }


        viewpager.setCurrentItem(titleArray.count() * 10, false)
    }
}
