package dev.kingbond.moveon.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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


        btnLoginWithPasswordContinue.setOnClickListener {
            val intent=Intent(context,MainActivity::class.java)
            startActivity(intent)
            clickListener.onClick()
        }
    }


}