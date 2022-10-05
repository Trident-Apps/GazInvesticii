package com.investgaz.gass.utils

import android.content.Context
import androidx.core.net.toUri
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.investgaz.gass.R
import java.util.*

class UriBuilder {

    fun buildUrl(data: MutableMap<String, Any>?, activity: Context, gadId: String): String {

        val url = activity.getString(R.string.base_url).toUri().buildUpon().apply {
            appendQueryParameter(
                activity.getString(R.string.secure_get_parametr),
                activity.getString(R.string.secure_key)
            )
            appendQueryParameter(activity.getString(R.string.dev_tmz_key), TimeZone.getDefault().id)
            appendQueryParameter(activity.getString(R.string.gadid_key), gadId)
            appendQueryParameter(activity.getString(R.string.deeplink_key), "null")
            appendQueryParameter(
                activity.getString(R.string.source_key), data?.get("media_source").toString()
            )
            appendQueryParameter(
                activity.getString(R.string.af_id_key),
                AppsFlyerLib.getInstance().getAppsFlyerUID(activity.applicationContext)
            )
            appendQueryParameter(
                activity.getString(R.string.adset_id_key),
                data?.get("adset_id").toString()
            )
            appendQueryParameter(
                activity.getString(R.string.campaign_id_key),
                data?.get("campaign_id").toString()
            )
            appendQueryParameter(
                activity.getString(R.string.app_campaign_key),
                data?.get("campaign").toString()
            )
            appendQueryParameter(
                activity.getString(R.string.adset_key),
                data?.get("adset").toString()
            )
            appendQueryParameter(
                activity.getString(R.string.adgroup_key),
                data?.get("adgroup").toString()
            )
            appendQueryParameter(
                activity.getString(R.string.orig_cost_key),
                data?.get("orig_cost").toString()
            )
            appendQueryParameter(
                activity.getString(R.string.af_siteid_key),
                data?.get("af_siteid").toString()
            )

        }.toString()
        return url
    }
}