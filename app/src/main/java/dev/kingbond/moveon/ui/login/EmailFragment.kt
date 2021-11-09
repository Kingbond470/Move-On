package dev.kingbond.moveon.ui.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.fragment_email.*
import java.util.regex.Pattern

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



        tvLoginSignUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_emailFragment_to_signUpFragment)
        }

        // if email is valid : continue to password
        btnLoginWithEmailContinue.setOnClickListener {
            var email = etLoginEmail.text.trim().toString()

            if (isValidEmail(email)) {
                Navigation.findNavController(view)
                    .navigate(R.id.action_emailFragment_to_passwordFragment)

            } else {
                // Enter your valid email ID
                etLoginEmail.error = "Enter valid email"
            }
        }


    }


    fun isValidEmail(email: CharSequence): Boolean {
        var isValid = true
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (!matcher.matches()) {
            isValid = false
        }
        return isValid
    }


}