package dev.kingbond.moveon.ui.login

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.fragment_email.*
import kotlinx.android.synthetic.main.fragment_email.tvLoginSignUp
import kotlinx.android.synthetic.main.fragment_password.*
import java.util.regex.Pattern

class EmailFragment : Fragment(R.layout.fragment_email) {


    private lateinit var mAuth: FirebaseAuth

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



        // with email
        mAuth= FirebaseAuth.getInstance()

        btnLoginWithEmailContinue.setOnClickListener {
            loginUser()
        }


        tvLoginSignUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_emailFragment_to_signUpFragment)
        }

        // to change the background color of button
        etLoginEmail.doAfterTextChanged {
            if (isValidEmail(etLoginEmail.text.trim().toString())) {
                // btnLoginWithEmailContinue.setBackgroundColor(Color.parseColor("#1E54B7"))
                val gradientDrawable = GradientDrawable()
                gradientDrawable.setColor(Color.parseColor("#1E54B7"))
                gradientDrawable.cornerRadius = 12F
                btnLoginWithEmailContinue.setBackgroundDrawable(gradientDrawable)
            } else {
                val gradientDrawable = GradientDrawable()
                gradientDrawable.setStroke(5, Color.parseColor("#1E54B7"))
                gradientDrawable.cornerRadius = 12F
                btnLoginWithEmailContinue.setBackgroundDrawable(gradientDrawable)
            }
        }

        // if email is valid : continue to password
//        btnLoginWithEmailContinue.setOnClickListener {
//            var email = etLoginEmail.text.trim().toString()
//
//            if (isValidEmail(email)) {
//
//
//                Navigation.findNavController(view)
//                    .navigate(R.id.action_emailFragment_to_passwordFragment)
//
//            } else {
//                // Enter your valid email ID
//                etLoginEmail.error = "Enter valid email"
//            }
//        }

        // to show and hide the password
        etLoginPasswordEmail.transformationMethod = PasswordTransformationMethod.getInstance()
        btnShowHideLoginEmail.setOnClickListener {
            if (etLoginPasswordEmail.transformationMethod == PasswordTransformationMethod.getInstance()) {
                etLoginPasswordEmail.transformationMethod = HideReturnsTransformationMethod.getInstance()
                btnShowHideLoginEmail.setBackgroundResource(R.drawable.password_show)

                //placing cursor at the end of the text
                etLoginPasswordEmail.setSelection(etLoginPasswordEmail.text.toString().length)
            } else {
                etLoginPasswordEmail.transformationMethod = PasswordTransformationMethod.getInstance()
                btnShowHideLoginEmail.setBackgroundResource(R.drawable.password_hide)

                //placing cursor at the end of the text
                etLoginPasswordEmail.setSelection(etLoginPasswordEmail.text.toString().length)
            }

        }



    }

    private fun loginUser() {
        val  email:String=etLoginEmail.text.trim().toString()
        val password:String=etLoginPasswordEmail.text.trim().toString()

        if (! isValidEmail(email)){
            Toast.makeText(context,"Invalid email",Toast.LENGTH_SHORT).show()

        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                            val intent=Intent(context,MainActivity::class.java)
                            startActivity(intent)
                        // finish the curent acitivity
                    }else{
                        Toast.makeText(
                            context,
                            "Error Message: ${task.exception?.message.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
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