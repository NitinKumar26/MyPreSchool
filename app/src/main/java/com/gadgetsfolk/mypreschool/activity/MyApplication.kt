package com.gadgetsfolk.mypreschool.activity

import android.app.Application
import android.os.Bundle
import android.util.Log
import com.gadgetsfolk.mypreschool.R
import com.gadgetsfolk.mypreschool.utility.AppOpenManager
import com.google.ads.mediation.unity.UnityMediationAdapter
import com.google.android.gms.ads.AdFormat
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.mediation.InitializationCompleteCallback
import com.google.android.gms.ads.mediation.MediationConfiguration
import com.unity3d.ads.metadata.MetaData
import java.util.*

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {
            RequestConfiguration.Builder().setTestDeviceIds(
                listOf("808EBC3F3CDB7990C5E47717B824C7AC")
            )
        }

        var appOpenManager:AppOpenManager? = AppOpenManager(this)


        //GDPR consent for Unity Personalized Ads
        val metaData = MetaData(this)
        metaData["gdpr.consent"] = true
        metaData.commit()

        val unityInterstitial = Bundle()
        unityInterstitial.putString("gameId", getString(R.string.unity_game_id))
        unityInterstitial.putString("zoneId", getString(R.string.unity_interstitial))

        val unityBanner = Bundle()
        unityBanner.putString("gameId", getString(R.string.unity_game_id))
        unityBanner.putString("zoneId", getString(R.string.unity_banner))

        val unityConfig: MutableList<MediationConfiguration> = ArrayList()
        unityConfig.add(MediationConfiguration(AdFormat.INTERSTITIAL, unityInterstitial))
        unityConfig.add(MediationConfiguration(AdFormat.BANNER, unityBanner))

        val adapter = UnityMediationAdapter()
        adapter.initialize(this, object : InitializationCompleteCallback {
            override fun onInitializationSucceeded() {}
            override fun onInitializationFailed(s: String) {
                Log.e("unityInit", s)
            }
        }, unityConfig)
    }
}