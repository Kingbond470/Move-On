package dev.kingbond.moveon.ui.coupons

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.activity_coupons.*

class CouponsActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupons)


        launchCouponFragment()

        coupon_btn.setOnClickListener {
            couponButton()
        }

        coin_btn.setOnClickListener {
            buttonCoin()
        }

        // return to home page
        backToHome.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun couponButton() {
        coupon_btn.setBackgroundResource(R.drawable.coupon_tab_bg)
        coupon_btn.setTextColor(0xFFFFFFFF.toInt())
        coin_btn.setBackgroundColor(Color.TRANSPARENT)
        coin_btn.setTextColor(0xFFF4511E.toInt())
        launchCouponFragment()
    }

    private fun buttonCoin() {
        coin_btn.setBackgroundResource(R.drawable.coupon_tab_bg)
        coin_btn.setTextColor(0xFFFFFFFF.toInt())
        coupon_btn.setBackgroundColor(Color.TRANSPARENT)
        coupon_btn.setTextColor(0xFFF4511E.toInt())
        launchCoinFragment()

    }

    private fun launchCouponFragment() {
        wallet_title.text = "12 Coupons Available"
        wallet_title.setTextColor(0xFFFB8C00.toInt())
        val couponFragment = CouponFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.wallet_fragment, couponFragment, "couponFragment").commit()
    }

    private fun launchCoinFragment() {
        wallet_title.text = "11 MFine Coins Available"
        wallet_title.setTextColor(0xFFFB8C00.toInt())
        val fragmentTransaction = fragmentManager.beginTransaction()
        val coinFragment = CoinFragment()
        fragmentTransaction.replace(R.id.wallet_fragment, coinFragment).commit()


    }

    private fun removeCouponFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(CouponFragment()).commit()
    }

    private fun removeCoinFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(CoinFragment()).commit()
    }
}