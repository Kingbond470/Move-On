package dev.kingbond.moveon.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.fragment_password.*

class PasswordFragment : Fragment(R.layout.fragment_password) {

    lateinit var clickListener: FinishActivityClickListner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Go to Main/Home Page
        btnLoginWithPasswordContinue.setOnClickListener {

            val password = etLoginPassword.text.trim().toString()

            // if length of password is greater than 8 : good password
            if (password.length >= 8) {

                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                clickListener.onClick()

            } else {
                etLoginPassword.error = "Length must be 8 characters"
            }
        }


        // to show and hide the password
        etLoginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        btnShowHideLogin.setOnClickListener {
            if (etLoginPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                etLoginPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                btnShowHideLogin.setBackgroundResource(R.drawable.password_show)

                //placing cursor at the end of the text
                etLoginPassword.setSelection(etLoginPassword.text.toString().length)
            } else {
                etLoginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                btnShowHideLogin.setBackgroundResource(R.drawable.password_hide)

                //placing cursor at the end of the text
                etLoginPassword.setSelection(etLoginPassword.text.toString().length)
            }

        }

    }


}