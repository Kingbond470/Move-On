package dev.kingbond.moveon.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.moveon.R
import android.view.WindowManager
import android.annotation.SuppressLint
import android.view.Window
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var count = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)
        imageView.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            fun onSwipeTop() {}
            fun onSwipeRight() {
                count = if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img)
                    textView.setText("Night")
                    1
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img)
                    textView.setText("Morning")
                    0
                }
            }

            fun onSwipeLeft() {
                count = if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img)
                    textView.setText("Night")
                    1
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img)
                    textView.setText("Morning")
                    0
                }
            }

            fun onSwipeBottom() {}
        })
    }
}