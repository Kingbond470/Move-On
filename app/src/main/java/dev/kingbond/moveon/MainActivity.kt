package dev.kingbond.moveon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import dev.kingbond.moveon.ui.about.AboutFragment
import dev.kingbond.moveon.ui.help.HelpFragment
import dev.kingbond.moveon.ui.profile.EditProfileActivity
import dev.kingbond.moveon.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout


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
        ibEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }



        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when (it.itemId) {
                R.id.nav_settings ->
                    replaceFragment(SettingsFragment(), it.title.toString())

                R.id.nav_about ->
                    replaceFragment(AboutFragment(), it.title.toString())

                R.id.nav_help ->
                    replaceFragment(HelpFragment(), it.title.toString())


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