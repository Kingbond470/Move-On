package dev.kingbond.moveon.ui.warehouses.SharedPref

import android.content.Context

class MyPreference(context: Context) {

    val PREF_EMAIL = "prefEmail"
    val PREF_key = "nameKey"

    val preference = context.getSharedPreferences(PREF_EMAIL, Context.MODE_PRIVATE)

    fun getName() : String? {
        return preference.getString(PREF_key, "Mintu saini")
    }

    fun setName(email: String){
        val editorr = preference.edit()
        editorr.putString(PREF_key,email)
        editorr.apply()
    }
    fun getEmail() : String? {
        return preference.getString(PREF_key, "Mintu saini")
    }

    fun setEmail(email: String){
        val editorr = preference.edit()
        editorr.putString(PREF_key,email)
        editorr.apply()
    }
    fun getPhone() : String? {
        return preference.getString(PREF_key, "9876876869")
    }

    fun setPhone(email: String){
        val editorr = preference.edit()
        editorr.putString(PREF_key,email)
        editorr.apply()
    }
}