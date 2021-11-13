package dev.kingbond.moveon.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.kingbond.moveon.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element

class AboutActivity : AppCompatActivity() {


    private val emailId = "kingbond470@gmail.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val versionElement: Element =
            Element().setTitle("Version 1.0").setIconDrawable(R.drawable.ic_tools)
                .setIconTint(R.color.colorPrimary)

        val linkedInIntent = Intent(Intent.ACTION_VIEW)
        linkedInIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse((String.format("https://www.linkedin.com/in/mausam-singh-5073451ab")))
        }

        val instaIntent = Intent(Intent.ACTION_VIEW)
        instaIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse((String.format("https://instagram.com/computer_science__student")))
        }

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse("mailto:$emailId")
            putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")
        }

        val linkedIn: Element = Element().setTitle("LinkedIn")
            .setIconDrawable(R.drawable.ic_linkedin_circle).setIntent(linkedInIntent)

        val instagram: Element = Element().setTitle("Instagram")
            .setIconDrawable(R.drawable.ic_instagram).setIntent(instaIntent)

        val email: Element = Element().setTitle("Email")
            .setIconDrawable(R.drawable.ic_email_about).setIntent(emailIntent)
            .setIconTint(R.color.yellow)

        val aboutPage = AboutPage(this, true)
            .setImage(R.drawable.ic_welcome)
            .setDescription("This is a Move On android application. It is designed by Aman and developed by Umang, Mintu and Mausam. Take a safe ride, move your packages and store in warehouses with the help of Move On. #StaySafe")
            .addGroup("Connect with Us")
            .addGitHub("kingbond470/Move-On", "Github")
            .addItem(linkedIn)
            .addItem(instagram)
            .addItem(email)
            .addGroup("About Move On")
            .addItem(versionElement)
            .create()
        setContentView(aboutPage)
    }
}