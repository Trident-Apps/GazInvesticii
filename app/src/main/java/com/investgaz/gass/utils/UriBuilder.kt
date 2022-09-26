package com.investgaz.gass.utils

import android.content.Context
import androidx.core.net.toUri
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.investgaz.gass.R
import java.util.*

class UriBuilder {

    fun buildUrl(data: MutableMap<String, Any>?, activity: Context): String {
        val gadId =
            AdvertisingIdClient.getAdvertisingIdInfo(activity.applicationContext!!).id.toString()
        val sb = StringBuilder()
        sb.append(activity.getString(R.string.base_url))
            .append("?")
            .append(activity.getString(R.string.secure_get_parametr))
            .append("=")
            .append(activity.getString(R.string.secure_key))
            .append("&")
            .append(activity.getString(R.string.dev_tmz_key))
            .append("=")
            .append(TimeZone.getDefault().id)
            .append("&")
            .append(activity.getString(R.string.gadid_key))
            .append("=")
            .append(gadId)
            .append("&")
            .append(activity.getString(R.string.af_id_key))
            .append("=")
            .append(AppsFlyerLib.getInstance().getAppsFlyerUID(activity.applicationContext))
            .append("&")
            .append(activity.getString(R.string.adset_id_key))
            .append("=")
            .append(data?.get(DATA_ADSET_ID).toString())
            .append("&")
            .append(activity.getString(R.string.campaign_id_key))
            .append("=")
            .append(data?.get(DATA_CAMPAIGN_ID).toString())
            .append("&")
            .append(activity.getString(R.string.app_campaign_key))
            .append("=")
            .append(data?.get(DATA_CAMPAIGN).toString())
            .append("&")
            .append(activity.getString(R.string.adset_key))
            .append("=")
            .append(data?.get(DATA_ADSET).toString())
            .append("&")
            .append(activity.getString(R.string.adgroup_key))
            .append("=")
            .append(data?.get(DATA_ADGROUP).toString())
            .append("&")
            .append(activity.getString(R.string.orig_cost_key))
            .append("=")
            .append(data?.get(DATA_ORIG_COST).toString())
            .append("&")
            .append(activity.getString(R.string.af_siteid_key))
            .append("=")
            .append(data?.get(DATA_AF_SITEID).toString())
        return sb.toString()
    }

    fun createUrl(
        data: MutableMap<String, Any>?,
        activity: Context?
    ): String {
        val gadId =
            AdvertisingIdClient.getAdvertisingIdInfo(activity?.applicationContext!!).id.toString()
        val url = activity.getString(R.string.base_url).toUri().buildUpon().apply {
            appendQueryParameter(
                activity.getString(R.string.secure_get_parametr),
                activity.getString(R.string.secure_key)
            )
            appendQueryParameter(activity.getString(R.string.dev_tmz_key), TimeZone.getDefault().id)
            appendQueryParameter(activity.getString(R.string.gadid_key), gadId)
            appendQueryParameter(
                activity.getString(R.string.af_id_key),
                AppsFlyerLib.getInstance().getAppsFlyerUID(activity.applicationContext)
            )

            appendQueryParameter(
                activity.getString(R.string.adset_id_key),
                data?.get(DATA_ADSET_ID).toString()
            )
            appendQueryParameter(
                activity.getString(R.string.campaign_id_key),
                data?.get(DATA_CAMPAIGN_ID).toString()
            )
            appendQueryParameter(
                activity.getString(R.string.app_campaign_key),
                data?.get(DATA_CAMPAIGN).toString()
            )
            appendQueryParameter(
                activity.getString(R.string.adset_key),
                data?.get(DATA_ADSET).toString()
            )
            appendQueryParameter(
                activity.getString(R.string.adgroup_key),
                data?.get(DATA_ADGROUP).toString()
            )
            appendQueryParameter(
                activity.getString(R.string.orig_cost_key),
                data?.get(DATA_ORIG_COST).toString()
            )
            appendQueryParameter(
                activity.getString(R.string.af_siteid_key),
                data?.get(DATA_AF_SITEID).toString()
            )

        }.toString()
        return url
    }

    companion object {
        val DATA_ADSET_ID = "adset_id"
        val DATA_CAMPAIGN_ID = "campaign_id"
        val DATA_CAMPAIGN = "campaign"
        val DATA_ADSET = "adset"
        val DATA_ADGROUP = "adgroup"
        val DATA_ORIG_COST = "orig_cost"
        val DATA_AF_SITEID = "af_siteid"
    }
}