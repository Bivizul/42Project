package aaa.bivizul.a42project.domain.util

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal
import com.onesignal.OneSignal.OSRemoteNotificationReceivedHandler

class Plaaviremnot : OSRemoteNotificationReceivedHandler {

    override fun remoteNotificationReceived(p0: Context?, p1: OSNotificationReceivedEvent?) {
        val plaavinot = p1!!.notification.additionalData.get("plaavi").toString()
        if (plaavinot.isNotEmpty()) {
            p0.let {
                Firebase.analytics.logEvent(plaavinot, null)
                AppsFlyerLib.getInstance().logEvent(p0, plaavinot, null)
                OneSignal.sendTag("conversion", plaavinot)
                OneSignal.sendTag("conversion_time", "${System.currentTimeMillis() / 1000L}")
                p1.complete(null)
            }
        }
    }
}