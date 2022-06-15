package ru.harlion.psy.utils

import android.content.Context
import com.android.billingclient.api.BillingClient
import ru.harlion.psy.AppApplication

class Prefs(val context: Context) {

    private val sharedPrefs = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    var isShowOnBoarding: Boolean
        get() = sharedPrefs.getBoolean("ON_BOARDING_SHOW", false)
        set(value) = sharedPrefs.edit().putBoolean("ON_BOARDING_SHOW", value)
            .apply()

    var isShowTestBeginAppUse: Boolean
        get() = sharedPrefs.getBoolean("TEST_SHOW", false)
        set(value) = sharedPrefs.edit().putBoolean("TEST_SHOW", value)
            .apply()

    var password: String
        get() = sharedPrefs.getString("PASSWORD", "") ?: ""
        set(value) = sharedPrefs.edit().putString("PASSWORD", value)
            .apply()

    var isPassword: Boolean
        get() = sharedPrefs.getBoolean("IS_PASSWORD", false)
        set(value) = sharedPrefs.edit().putBoolean("IS_PASSWORD", value).apply()

    var purchaseToken: String?
        get() = sharedPrefs.getString("PURCHASE_TOKEN", null)
        set(value) = sharedPrefs.edit().putString("PURCHASE_TOKEN", value)
            .apply()

    var isPremium: Boolean
        get() = if (sharedPrefs.getBoolean("IS_PREMIUM", false)) {
            if (purchaseToken != null) {
                (context.applicationContext as AppApplication).clientWrapper.acknowledgePurchase(
                    purchaseToken!!
                ) { billingResult ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        purchaseToken = null
                    }
                }
            }
            true
        } else false
        set(value) = sharedPrefs.edit().putBoolean("IS_PREMIUM", value)
            .apply()
}