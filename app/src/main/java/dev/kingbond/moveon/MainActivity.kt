package dev.kingbond.moveon

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.moveon.ui.about.AboutActivity
import dev.kingbond.moveon.ui.coupons.CouponsActivity
import dev.kingbond.moveon.ui.help.HelpFragment
import dev.kingbond.moveon.ui.home.MapFragment
import dev.kingbond.moveon.ui.home.recyclerview.VehicleAdapter
import dev.kingbond.moveon.ui.home.recyclerview.VehicleClickListener
import dev.kingbond.moveon.ui.home.recyclerview.Vehicles
import dev.kingbond.moveon.ui.login.Login
import dev.kingbond.moveon.ui.packageandmovers.BaseActivityPackersAndMovers
import dev.kingbond.moveon.ui.profile.EditProfileActivity
import dev.kingbond.moveon.ui.settings.SettingsFragment
import dev.kingbond.moveon.ui.warehouses.whPersonalDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.booking_confirmed_layout.*
import kotlinx.android.synthetic.main.booking_vehicle_layout.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.vehivle_info_layout.*
import java.io.IOException

class MainActivity : FragmentActivity(), OnMapReadyCallback, LocationListener,
    VehicleClickListener {

    private var listOfVehicles = ArrayList<Vehicles>()

    private var from: String = ""
    private var to: String = ""

    private lateinit var bottomSheetDialogForDirections: BottomSheetDialog
    private lateinit var bottomSheetDialogForBookedVehicle: BottomSheetDialog
    private lateinit var bottomSheetDialogForVehicleInfo: BottomSheetDialog

    var map: GoogleMap? = null
    private var currentLongitude: Double = 0.0
    private var currentLatitude: Double = 0.0

    private var currentLocation: LatLng? = null

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    //profile
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forMap()

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // for warehouses
        cvWarehouses.setOnClickListener {
            val intent = Intent(this, whPersonalDetailsActivity::class.java)
            startActivity(intent)
        }

        // for packers and movers
        cvPackersAndMovers.setOnClickListener {
            val intent = Intent(this, BaseActivityPackersAndMovers::class.java)
            startActivity(intent)
        }


        // to access the view from header in Navigation View
        val header: View = navView.getHeaderView(0)
        val ibEdit: ImageButton = header.findViewById(R.id.ibEditProfile)
        //val ivImage:CircleImageView=header.findViewById(R.id.ivProfileImageHeaderLayout)
        ibEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }


        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if (user != null) {

            //  Glide.with(header.ivProfileImageHeaderLayout).load(user.).into(header.ivProfileImageHeaderLayout)
            //  val tvProfileEmail:TextView=header.findViewById(R.id.tvEditProfileMail)
            //   tvProfileEmail.text = user.email
            header.tvEditProfileMail.text = user.email
            // header.tvUsernameProfileHeader.text = user.phoneNumber

            //   Glide.with(ivImage).load(user.photoUrl).into(ivImage)
            //  tvSettingsProfileName.text = user.displayName

        }

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when (it.itemId) {
                R.id.nav_settings ->
                    replaceFragment(SettingsFragment(), it.title.toString())

                R.id.nav_about -> {

                    //  replaceFragment(AboutFragment(), it.title.toString())
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }

                R.id.nav_help ->
                    replaceFragment(HelpFragment(), it.title.toString())

                R.id.nav_coupons -> {
                    val intent = Intent(this, CouponsActivity::class.java)
                    startActivity(intent)
                }

                R.id.nav_logout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }

            }
            true

        }


    }


    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

        // to close the drawer
        drawerLayout.closeDrawers()
        // to set the title on top
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true

        return super.onOptionsItemSelected(item)
    }

    private fun forMap() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        //current location
        getCurrentLocation()
        Handler().postDelayed({
            getCurrentLocation()
        }, 1500)


        idSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val location = idSearchView.query.toString()

                var addressList: List<Address>? = null

                if (location != null || location == "") {
                    val geocoder = Geocoder(this@MainActivity)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    val address = addressList!![0]
                    val latLng = LatLng(address.latitude, address.longitude)
                    map!!.clear()
                    map!!.addMarker(
                        MarkerOptions().position(currentLocation!!).title("Current Location")
                    )!!
                        .setIcon(
                            bitmapFromVector(
                                this@MainActivity,
                                R.drawable.ic_user_map_marker
                            )
                        )
                    map!!.addMarker(MarkerOptions().position(latLng).title(location))!!
                    map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
                }

                to = location
                showBottomSheetForDirections()

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


        // at last we calling our map fragment to update.
        mapFragment!!.getMapAsync(this)


        //get current location
        fabGetCurrentLocation.setOnClickListener {
            getCurrentLocation()
        }

        //search implementation
        idEditTextView.setOnClickListener {
            frameLayoutForShowOff.visibility = View.GONE
            frameLayoutForSearch.visibility = View.VISIBLE
        }

        btnGetDirections.setOnClickListener {
            frameLayoutForSearch.visibility = View.GONE
            showBottomSheetForDirections()
            frameLayoutForShowOff.visibility = View.VISIBLE
        }

        icBack.setOnClickListener {
            frameLayoutForSearch.visibility = View.GONE
            frameLayoutForShowOff.visibility = View.VISIBLE
//            val intent = Intent(this, MainActivity2::class.java)
//            startActivity(intent)

        }
    }


    private fun showBottomSheetForDirections() {
        bottomSheetDialogForDirections = BottomSheetDialog(this@MainActivity)
        val viewDirection = layoutInflater.inflate(
            R.layout.booking_vehicle_layout,
            null
        )
        bottomSheetDialogForDirections.setContentView(viewDirection)

        bottomSheetDialogForDirections.etSearchFrom.setText("Your Location")
        bottomSheetDialogForDirections.etSearchTo.setText(to)

        //vehicle recycler view
        for (i in 1..10) {
            listOfVehicles.add(
                Vehicles(
                    R.drawable.vehicle_tata_ace,
                    "Tata Ace",
                    "MH-48-AY-4977",
                    701,
                    "7ft X 4ft X 5ft",
                    "811.76",
                    "12 min",
                    R.drawable.dwayne_johnson,
                    "Dwayne Johnson",
                    "7218558435"
                )
            )
        }

        val linearLayoutManager = LinearLayoutManager(this)
        val adapter = VehicleAdapter(listOfVehicles, this)
        bottomSheetDialogForDirections.rcvVehicles.adapter = adapter
        bottomSheetDialogForDirections.rcvVehicles.layoutManager = linearLayoutManager

        bottomSheetDialogForDirections.show()
    }

    @SuppressLint("SetTextI18n")
    private fun bottomSheetForVehicleInfo(vehicle: Vehicles, position: Int) {
        bottomSheetDialogForVehicleInfo = BottomSheetDialog(this@MainActivity)
        val viewDirection =
            layoutInflater.inflate(R.layout.vehivle_info_layout, null)
        bottomSheetDialogForVehicleInfo.setContentView(viewDirection)

        bottomSheetDialogForVehicleInfo.show()

        bottomSheetDialogForVehicleInfo.vehicleInfoImage.setImageResource(vehicle.vehicleImage)
        bottomSheetDialogForVehicleInfo.tvVehicleCapacity.text =
            vehicle.vehicleCapacity.toString() + " kg"
        bottomSheetDialogForVehicleInfo.tvVehicleSize.text = vehicle.vehicleSize

        bottomSheetDialogForVehicleInfo.btnOkay.setOnClickListener {
            bottomSheetDialogForVehicleInfo.cancel()
        }

    }

    private fun bottomSheetForBookedVehicle(vehicle: Vehicles, position: Int) {
        bottomSheetDialogForBookedVehicle = BottomSheetDialog(this@MainActivity)
        val viewVehicleBooked = layoutInflater.inflate(
            R.layout.booking_confirmed_layout,
            null
        )
        bottomSheetDialogForBookedVehicle.setContentView(viewVehicleBooked)

        bottomSheetDialogForDirections.cancel()
        bottomSheetDialogForBookedVehicle.show()

        bottomSheetDialogForBookedVehicle.ivBookedVehicleImage.setImageResource(vehicle.vehicleImage)
        bottomSheetDialogForBookedVehicle.tvBookedVehiclePrice.text = vehicle.price
        bottomSheetDialogForBookedVehicle.driverImage.setImageResource(vehicle.driverImage)
        bottomSheetDialogForBookedVehicle.tvDriverName.text = vehicle.driverName
        bottomSheetDialogForBookedVehicle.tvVehicleName.text = vehicle.vehicleName
        bottomSheetDialogForBookedVehicle.tvVehicleNumber.text = vehicle.vehicleNumber

        Handler().postDelayed({
            bottomSheetDialogForBookedVehicle.driverDetailsLayout.visibility = View.VISIBLE
            bottomSheetDialogForBookedVehicle.searchingForVehicleLayout.visibility = View.GONE
        }, 2000)

        bottomSheetDialogForBookedVehicle.ivCallDriver.setOnClickListener {
            Toast.makeText(this, "calling the driver", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${vehicle.driverNumber}")
            startActivity(intent)
        }

        bottomSheetDialogForBookedVehicle.cancelBooking.setOnClickListener {
            bottomSheetDialogForBookedVehicle.driverDetailsLayout.visibility = View.GONE
            bottomSheetDialogForBookedVehicle.searchingForVehicleLayout.visibility = View.VISIBLE
            bottomSheetDialogForBookedVehicle.cancel()
            bottomSheetDialogForDirections.show()
        }
    }

    override fun onVehicleClick(vehicle: Vehicles, position: Int) {
        Toast.makeText(this, "View Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onVehicleInfoClick(vehicle: Vehicles, position: Int) {
        Toast.makeText(this, "Vehicle Info Clicked", Toast.LENGTH_SHORT).show()
        bottomSheetForVehicleInfo(vehicle, position)
    }

    override fun onAddLabourClick(vehicle: Vehicles, position: Int, labourCount: Int) {
        Toast.makeText(this, "Add Labour Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onBookVehicleClick(vehicle: Vehicles, position: Int) {
        Toast.makeText(this, "Book Vehicle Clicked", Toast.LENGTH_SHORT).show()
        bottomSheetForBookedVehicle(vehicle, position)
    }

    private fun bitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
//        map!!.mapType = GoogleMap.MAP_TYPE_HYBRID

        map!!.setOnMapClickListener { latLng -> // Creating a marker
            val markerOptions = MarkerOptions()

            // Setting the position for the marker
            markerOptions.position(latLng)
            markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)

            // Clears the previously touched position
            map!!.clear()

            map!!.addMarker(MarkerOptions().position(currentLocation!!).title("Current Location"))!!
                .setIcon(
                    bitmapFromVector(
                        this@MainActivity,
                        R.drawable.ic_user_map_marker
                    )
                )

            // Animating to the touched position
            map!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))

            // Placing a marker on the touched position
            map!!.addMarker(markerOptions)
        }

    }

    private fun getCurrentLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    override fun onLocationChanged(location: Location) {
        map!!.clear()
//        textView.text = "Latitude: " + location.latitude + " , Longitude: " + location.longitude
        currentLatitude = location.latitude
        currentLongitude = location.longitude
        currentLocation = LatLng(currentLatitude, currentLongitude)
        map!!.addMarker(MarkerOptions().position(currentLocation!!).title("Maharashtra"))!!
            .setIcon(bitmapFromVector(this, R.drawable.ic_user_map_marker))
        map!!.moveCamera(CameraUpdateFactory.newLatLng(currentLocation!!))
        val zoom: CameraUpdate = CameraUpdateFactory.zoomTo(15F)
        map!!.animateCamera(zoom)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}