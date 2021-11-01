package dev.kingbond.moveon.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.kingbond.moveon.MainActivity
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.activity_login2.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

//    companion object {
//         const val RC_SIGN_IN = 120
//    }
//
//    private lateinit var mAuth: FirebaseAuth
//    private lateinit var googleSignInClient: GoogleSignInClient
//    private var TAG: String = "Login"


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // sign in with email
////        btnLoginWithEmail.setOnClickListener {
////            startActivity(Intent(context, SignUpFragment::class.java))
////
////
////        }
//
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        googleSignInClient = GoogleSignIn.getClient(context, gso)
//
//        mAuth = FirebaseAuth.getInstance()
////        btnLoginWithGoogle.setOnClickListener {
////            signIn()
////        }
//
////        btnSignUpPhone.setOnClickListener {
////            setCurrentFragment(SignUpPhoneNumber())
////        }
//    }
//
//    private fun signIn() {
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, LoginFragment.RC_SIGN_IN)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == LoginFragment.RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            val exception = task.exception
//            if (task.isSuccessful) {
//                try {
//                    // Google Sign In was successful, authenticate with Firebase
//                    val account = task.getResult(ApiException::class.java)!!
//                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
//                    firebaseAuthWithGoogle(account.idToken!!)
//                } catch (e: ApiException) {
//                    // Google Sign In failed, update UI appropriately
//                    Log.w(TAG, "Google sign in failed", e)
//                }
//            } else {
//                Log.w("SignUpActivity", exception.toString())
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        activity?.let {
//            mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(it) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithCredential:success")
//                        val user = mAuth.currentUser
//                        val intent = Intent(context, MainActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithCredential:failure", task.exception)
//
//                    }
//                }
//        }
//    }




}