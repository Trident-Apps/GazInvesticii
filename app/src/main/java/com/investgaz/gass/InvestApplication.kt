package com.investgaz.gass

import android.app.Application
import com.onesignal.OneSignal

class InvestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        OneSignal.setAppId(applicationContext.getString(R.string.onesignal_id))
        OneSignal.initWithContext(applicationContext)
    }

    companion object {
        lateinit var gadId: String
    }
}