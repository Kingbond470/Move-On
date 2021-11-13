package dev.kingbond.moveon.Swipe

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import dev.kingbond.moveon.R

private const val ARG_BACKGROUND_COLOR = "param1"
private const val ARG_RESOURCE = "param2"
private const val ARG_TITLE = "param3"
private const val ARG_Heading = "param4"


class Swipe : Fragment() {
    private var param1: Int? = null
    private var param2: Int? = null
    private var param3: String? = null
    private var param4: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, container, false).apply {
            setBackgroundColor(param1 ?: Color.RED)

            findViewById<LottieAnimationView>(R.id.lottieAnimationView).setAnimation(
                param2 ?: R.raw.orderdelivered
            )
            findViewById<LottieAnimationView>(R.id.lottieAnimationView).repeatCount =
                LottieDrawable.INFINITE
            findViewById<LottieAnimationView>(R.id.lottieAnimationView).repeatMode =
                LottieDrawable.RESTART
            findViewById<LottieAnimationView>(R.id.lottieAnimationView).playAnimation()

            findViewById<TextView>(R.id.tvDescription).text =
                param3 ?: "Hello fellow developer!"
            findViewById<TextView>(R.id.tvHeading).text =
                param4 ?: "Default Heading!"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: Int, param3: String, param4: String) =
            Swipe().apply {
                arguments = Bundle().apply {
                    putInt(ARG_BACKGROUND_COLOR, param1)
                    putInt(ARG_RESOURCE, param2)
                    putString(ARG_TITLE, param3)
                    putString(ARG_Heading, param4)

                }
            }
    }
}
