package com.investgaz.gass.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.investgaz.gass.R
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {

            val gadId =
                AdvertisingIdClient.getAdvertisingIdInfo(application.applicationContext).id.toString()
            OneSignal.initWithContext(application.applicationContext)
            OneSignal.setAppId(this@MainActivity.getString(R.string.onesignal_id))
            OneSignal.setExternalUserId(gadId)
        }
    }
}