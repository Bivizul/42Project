@file:Suppress("DEPRECATION")

package aaa.bivizul.a42project.domain.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.onesignal.OneSignal
import java.text.SimpleDateFormat
import java.util.*

fun getPlaavimm(): String {
    val manfacplaavi = android.os.Build.MANUFACTURER
    val modelplaavi = android.os.Build.MODEL
    return "$manfacplaavi $modelplaavi"
}

fun getPlaavisim(plaavicon: Context): String {
    val telmanplaavi = plaavicon.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return telmanplaavi.simCountryIso
}

fun getPlaaviid(plaavicon: Context): String {
    val sharedPreferences = plaavicon.getSharedPreferences("appprefplaavi", Context.MODE_PRIVATE)
    var plaaviid = sharedPreferences.getString("plaavi_key", "noplaavi") ?: "noplaavi"
    if (plaaviid == "noplaavi") {
        val dateNow = Date()
        val simpleDateFormat = SimpleDateFormat("yyMMddhhmmssMs")
        val datetime = simpleDateFormat.format(dateNow)
        val randomNum = (10000 until 100000).random()
        plaaviid = datetime + randomNum
        sharedPreferences.edit().putString("plaavi_key", plaaviid).apply()
    }
    return plaaviid
}

fun getPlaavil(): String {
    return Locale.getDefault().language
}

fun getPlaavit(): String {
    val plaavitz: String = SimpleDateFormat("z", Locale.getDefault()).format(
        Calendar.getInstance(
            TimeZone.getTimeZone("GMT"),
            Locale.getDefault()
        ).time
    ).replace("GMT", "")
    val plaavizone = if (plaavitz.contains(":")) plaavitz else "default"
    return plaavizone
}

fun getPlaavidlg(plaavicon: Context, plaaviact: Activity) {
    AlertDialog.Builder(plaavicon).apply {
        setTitle("Error connect!")
        setMessage("Please try again later")
        setPositiveButton("Quit") { _, _ ->
            plaaviact.finish()
            System.exit(0)
        }
        setCancelable(true)
    }.create().show()
}

@SuppressLint("MissingPermission")
fun checkPlaavinet(plaavicon: Context): Boolean {
    val conmanplaavi =
        plaavicon.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netinfplaavi = conmanplaavi.activeNetworkInfo
    return netinfplaavi != null && netinfplaavi.isConnected
}

fun sigPlaavioff() {
    OneSignal.disablePush(true)
}

fun getPlaavifit(plaavis: String): String {
    val plaavimin = plaavis.indexOf("view_id=") + 8
    val plaavimax = plaavis.indexOf("&stream")
    return plaavis.substring(plaavimin, plaavimax)
}

fun getPlaaviScrOrnt(plaaviornt: Resources): String {
    val ornt = plaaviornt.configuration.orientation
    return if (ornt == Configuration.ORIENTATION_PORTRAIT) {
        "http://65.109.10.118/42Project/aviv.jpg"
    } else {
        "http://65.109.10.118/42Project/avih.jpg"
    }
}