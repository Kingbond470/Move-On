package dev.kingbond.moveon.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.fragment_password.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.util.regex.Pattern

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        btnSignUpContinue.setOnClickListener {
            registerUser()
        }

        // for  : validation
        var fullname = etFullNameSignUp.text.trim().toString()
        var email = etEmailSignUp.text.trim().toString()
        var phone = etPhoneNumberSignUp.text.trim().toString()
        var password = etPasswordSignUp.text.trim().toString()
        var confirmPassword = etConfirmPasswordSignUp.text.trim().toString()
        var checkPass = false

//        if (isPhoneLength(password) && isPhoneLength(confirmPassword)) {
//            checkPass = compareTwoPasswords(password, confirmPassword)
//        }

//        btnSignUpContinue.setOnClickListener {
//
//            // to validate all things at once : before opening Home page
//            if (isFullName(fullname) && isValidEmail(email) && isPhoneLength(phone) && checkPass) {
//                Navigation.findNavController(view)
//                    .navigate(R.id.action_signUpFragment_to_emailFragment)
//            } else {
//                Toast.makeText(context, "Please enter valid details", Toast.LENGTH_SHORT).show()
//            }
        //      }

        // to show and hide the password
        etPasswordSignUp.transformationMethod = PasswordTransformationMethod.getInstance()
        btnShowHideSignUpFirst.setOnClickListener {
            if (etPasswordSignUp.transformationMethod == PasswordTransformationMethod.getInstance()) {
                etPasswordSignUp.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                btnShowHideSignUpFirst.setBackgroundResource(R.drawable.password_show)

                //placing cursor at the end of the text
                etPasswordSignUp.setSelection(etLoginPassword.text.toString().length)
            } else {
                etPasswordSignUp.transformationMethod = PasswordTransformationMethod.getInstance()
                btnShowHideSignUpFirst.setBackgroundResource(R.drawable.password_hide)

                //placing cursor at the end of the text
                etPasswordSignUp.setSelection(etLoginPassword.text.toString().length)
            }
        }


        // to show and hide the Confirm password
        etConfirmPasswordSignUp.transformationMethod = PasswordTransformationMethod.getInstance()
        btnShowHideSignUpSecond.setOnClickListener {
            if (etConfirmPasswordSignUp.transformationMethod == PasswordTransformationMethod.getInstance()) {
                etConfirmPasswordSignUp.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                btnShowHideSignUpSecond.setBackgroundResource(R.drawable.password_show)

                //placing cursor at the end of the text
                etConfirmPasswordSignUp.setSelection(etLoginPassword.text.toString().length)
            } else {
                etConfirmPasswordSignUp.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                btnShowHideSignUpSecond.setBackgroundResource(R.drawable.password_hide)

                //placing cursor at the end of the text
                etConfirmPasswordSignUp.setSelection(etLoginPassword.text.toString().length)
            }
        }
    }

    private fun registerUser() {
        var fullname = etFullNameSignUp.text.trim().toString()
        var email = etEmailSignUp.text.trim().toString()
        var phone = etPhoneNumberSignUp.text.trim().toString()
        var password = etPasswordSignUp.text.trim().toString()

        if (!isFullName(fullname)) {
            Toast.makeText(context, "Enter Valid Name", Toast.LENGTH_SHORT).show()
        } else if (!isValidEmail(email)) {
            Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show()
        } else if (!isPhoneLength(phone)) {
            Toast.makeText(context, "Enter valid phone number", Toast.LENGTH_SHORT).show()

        } else if (!isPasswordLengthValid(password)) {
            Toast.makeText(context, "Enter valid password", Toast.LENGTH_SHORT).show()
        } else if (password != etConfirmPasswordSignUp.text.trim().toString()) {
            Toast.makeText(
                context,
                "Password and confirm password should be same",
                Toast.LENGTH_SHORT
            ).show()
        } else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users")
                        .child(firebaseUserId)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["username"] = fullname
                    userHashMap["profile"] =
                        "https://firebasestorage.googleapis.com/v0/b/move-on-33695.appspot.com/o/man.png?alt=media&token=838b7304-249b-4985-be0b-b15751cc4f78"
                    userHashMap["status"] = "offline"
                    userHashMap["number"] = phone
                    userHashMap["user_email"] = email
                    userHashMap["user_password"] = password

                    refUsers.updateChildren(userHashMap)
                        .addOnCompleteListener { task ->

                            //  val intent=Intent(context,Login::class.java)
                            //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            //   startActivity(intent)
//                            val intent = Intent(context, Login::class.java)
//                            startActivity(intent)
                            if (task.isSuccessful) {
                                val intent = Intent(context, Login::class.java)
                                startActivity(intent)
                                Toast.makeText(
                                    context,
                                    "User registered successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }


                } else {
                    Toast.makeText(
                        context,
                        "Error Message: ${task.exception?.message.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }
    }


    // to validate user email input
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


    // full name : validation
    fun isFullName(fullName: String): Boolean {
        if (fullName.length >= 3) return true
        return false
    }

    // password : validation
    fun isPasswordLengthValid(password: String): Boolean {
        if (password.length >= 8) {
            return true
        }
        return false
    }

    // compare two passwords, if equal : return true
    fun compareTwoPasswords(pass: String, confrimPass: String): Boolean {
        if (pass.equals(confrimPass)) return true
        return false
    }

    fun isPhoneLength(phone: String): Boolean {
        if (phone.length == 10) return true
        return false
    }

    //phone number validation
//    fun isPhoneValid(phoneNumber:String):Boolean{
//         val REG = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
//        var PATTERN: Pattern = Pattern.compile(REG)
//        fun CharSequence.isPhoneNumber() : Boolean = PATTERN.matcher(phoneNumber).find()
//    }

}