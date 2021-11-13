package dev.kingbond.moveon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.moveon.ui.about.AboutActivity
import dev.kingbond.moveon.ui.coupons.CouponsActivity
import dev.kingbond.moveon.ui.help.HelpFragment
import dev.kingbond.moveon.ui.login.Login
import dev.kingbond.moveon.ui.profile.EditProfileActivity
import dev.kingbond.moveon.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    //profile
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


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
}