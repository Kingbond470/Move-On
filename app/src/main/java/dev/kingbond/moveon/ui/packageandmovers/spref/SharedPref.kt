package dev.kingbond.moveon.ui.packageandmovers.spref

import android.content.Context
import android.content.SharedPreferences

class SharedPref {

    var prefs: SharedPreferences? = null
    var context: Context? = null



    fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    fun insertData(context: Context, key: String?, value: String?) {
        val editor = getPrefs(context).edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun insertIntData(context: Context, key: String?, value: Int?) {
        val editor = getPrefs(context).edit()
        if (value != null) {
            editor.putInt(key, value)
        }
        editor.apply()
    }

    fun insertStringSet(context: Context, key: String?, value: Set<String?>?) {
        getPrefs(context).edit().putStringSet(key, value).apply()
    }

    fun retriveData(context: Context, key: String?): String? {
        return getPrefs(context).getString(key, "no_data_found")
    }

    fun retriveIntData(context: Context, key: String?): Int? {
        return getPrefs(context).getInt(key, 0)
    }

    fun getStringSet(context: Context, key: String?): Set<String?>? {
        return getPrefs(context).getStringSet(key, null)
    }

    fun deleteData(context: Context, key: String?) {
        val editor = getPrefs(context).edit()
        editor.remove(key)
        editor.apply()
    }


}