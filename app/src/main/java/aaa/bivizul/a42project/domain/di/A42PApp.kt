package aaa.bivizul.a42project.domain.di

import android.app.Application
import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal

class A42PApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(PLAAVIOSAI)

        AppsFlyerLib.getInstance().init(PLAAVIAF, object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {}
            override fun onConversionDataFail(p0: String?) {}
            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
            override fun onAttributionFailure(p0: String?) {}
        }, this)
        AppsFlyerLib.getInstance().start(this)
    }

    companion object {
        const val PLAAVIOSAI = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
        const val PLAAVIAF = "xxxxxxxxxxxxxxxxxxxxxx"
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is A42PApp -> appComponent
        else -> applicationContext.appComponent
    }