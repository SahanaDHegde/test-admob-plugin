package com.getcapacitor.community.admob.helpers

import com.getcapacitor.JSObject
import com.getcapacitor.community.admob.models.AdMobPluginError
import com.getcapacitor.community.admob.models.LoadPluginEventNames
import com.getcapacitor.community.admob.rewarded.RewardAdPluginEvents
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.common.util.BiConsumer

open class FullscreenPluginCallback(private val loadPluginObject: LoadPluginEventNames,
                                    private val notifyListenersFunction: BiConsumer<String, JSObject>): FullScreenContentCallback() {

    override fun onAdShowedFullScreenContent() {
        notifyListenersFunction.accept(loadPluginObject.Showed, JSObject())
    }

    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
        val adMobError = AdMobPluginError(adError)
        notifyListenersFunction.accept(
                loadPluginObject.FailedToShow, adMobError
        )
    }

    override fun onAdDismissedFullScreenContent() {
        notifyListenersFunction.accept(loadPluginObject.Dismissed, JSObject())
    }

    override fun onAdClicked() {
        notifyListenersFunction.accept(RewardAdPluginEvents.RewardedAdClicked, JSObject())
        System.out.println("event clicked 3  click on juego test user click");
    }
}