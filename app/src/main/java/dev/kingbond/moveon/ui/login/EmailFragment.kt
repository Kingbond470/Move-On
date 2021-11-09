package dev.kingbond.moveon.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.fragment_email.*

class EmailFragment : Fragment(R.layout.fragment_email) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)


        // Navigation
       // findNavController().navigate(R.id.action_emailFragment_to_passwordFragment)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLoginWithEmailContinue.setOnClickListener {
         //   findNavController().navigate(R.id.action_emailFragment_to_passwordFragment)
            Navigation.findNavController(view).navigate(R.id.action_emailFragment_to_passwordFragment)
        }

        tvLoginSignUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_emailFragment_to_signUpFragment)
        }
    }




}