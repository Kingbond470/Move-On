package dev.kingbond.moveon.ui.login

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.GestureDetector
import java.lang.Exception

open class OnSwipeTouchListener(applicationContext: Context) : View.OnTouchListener {

    private var gestureDetector: GestureDetector? = null

    fun OnSwipeTouchListener(ctx: Context?) {
        gestureDetector = GestureDetector(ctx, GestureListener())
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector!!.onTouchEvent(event)
    }

    private class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwiperRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }

        companion object {
            private const val SWIPE_THRESHOLD = 100
            private const val SWIPE_VELOCITY_THRESHOLD = 100
        }
    }
}

fun onSwiperRight() {
}

fun onSwipeLeft() {
}

fun onSwipeTop() {
}

fun onSwipeBottom() {
}
