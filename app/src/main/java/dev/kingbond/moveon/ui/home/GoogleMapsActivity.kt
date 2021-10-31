package dev.kingbond.moveon.ui.home

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.activity_google_maps.*

class GoogleMapsActivity : AppCompatActivity() {

    private val TAG: String = "Google Maps User "
    private val ERROR_DIALOG_REQUEST: Int = 9001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)
        if (isService()) {
            init()
        }
    }

    fun init() {
        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    fun isService(): Boolean {
        Log.d(TAG, "isServiceOk: checking google services versions")

        var available: Int =
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

        if (available == ConnectionResult.SUCCESS) {
            // everything is fine, user can make request
            Log.d(TAG, "Google play services is working")
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // an error occured but we can resolve it
            Log.d(TAG, "an error occured but we can fix it")
            var dialog: Dialog = GoogleApiAvailability.getInstance()
                .getErrorDialog(this, available, ERROR_DIALOG_REQUEST)
            dialog.show()
        } else {
            Toast.makeText(this, "You can't make map request ", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}