package dev.kingbond.moveon.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.moveon.R
import dev.kingbond.moveon.ui.login.Login
import kotlinx.android.synthetic.main.fragment_settings_profile.*


class SettingsProfileFragment : Fragment(R.layout.fragment_settings_profile) {

    //profile
    private lateinit var mAuth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //profile
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if (user != null) {
            Glide.with(ivEditProfileImage).load(user.photoUrl).into(ivEditProfileImage)
            tvEditProfileName.text = user.displayName
        }

        tvEditLogout.setOnClickListener {
            mAuth.signOut()
            val logout = Intent(activity, Login::class.java)
            startActivity(logout)
            (activity as Activity?)?.overridePendingTransition(0, 0)
        }

    }

}