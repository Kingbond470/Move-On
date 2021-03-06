package dev.kingbond.moveon.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import dev.kingbond.moveon.ui.login.Login
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    //profile
    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llViewProfile.setOnClickListener {
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout,
                SettingsProfileFragment(),
                "EditProfile Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

        //profile
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if (user != null) {
            Glide.with(ivSettingProfileImage).load(user.photoUrl).into(ivSettingProfileImage)
            tvSettingsProfileName.text = user.displayName
            ivEmail.text = user.email
            tvLogOutName.text = "You are logged in as ${user.displayName}"
            Glide.with(ivSettingProfileImage).load(user.photoUrl).into(ivSettingProfileImage)
            tvSettingsProfileName.text = user.displayName
            ivEmail.text = user.email
            tvLogOutName.text = "You are logged in as ${user.displayName}"
        } else {
            Glide.with(ivSettingProfileImage).load(R.drawable.man).into(ivSettingProfileImage)
            tvSettingsProfileName.text = "Masai Android"
        }

        // back to mainactivity
//        cdSettingsProfile.setOnClickListener {
//            val intent = Intent(context, MainActivity::class.java)
//            startActivity(intent)
//        }

        //logout
        tvLogOut.setOnClickListener {
            mAuth.signOut()
            val logout = Intent(activity, Login::class.java)
            startActivity(logout)
            (activity as Activity?)?.overridePendingTransition(0, 0)
        }

        if (spinnerSettingDownload.count > 1) {
            spinnerSettingDownload.setSelection(1)
        }

        seekbarSettingCrossfade.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (progress >= 0 && progress <= seekBar.max) {
                        val progressString = (progress).toString() + " s"
                        tvCrossfade12s.text = progressString // the TextView Reference
                        seekBar.secondaryProgress = progress
                    }
                }
            }
        })

        //for storage
        val statFs = StatFs(Environment.getDataDirectory().absolutePath);
        var total = (statFs.blockCount.toFloat() * statFs.blockSize) / 1048576;
        var free = (statFs.availableBlocks.toFloat() * statFs.blockSize) / 1048576;
        total /= 1024
        free /= 1024
        total = String.format("%.1f", total).toFloat()
        free = String.format("%.1f", free).toFloat()

        val busy = String.format("%.1f", (total - free)).toFloat()

        indicator.max = total.toInt()
        indicator.progress = busy.toInt()
        tvFreeSpace.text = "$free GB"
        tvUsedSpace.text = "$busy GB"

        val cache = context?.cacheDir.toString()
        tvCacheSpace.text = "2.2 MB"

        llDeleteCache.setOnClickListener {
            context?.cacheDir?.deleteRecursively()
            rlCache.visibility = View.GONE
        }

    }
}