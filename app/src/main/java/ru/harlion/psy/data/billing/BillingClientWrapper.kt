package ru.harlion.psy.data.billing

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.*
import ru.harlion.psy.utils.Prefs

class BillingClientWrapper(val context: Context) : PurchasesUpdatedListener {

    private val billingClient = BillingClient
        .newBuilder(context)
        .enablePendingPurchases()
        .setListener(this)
        .build()

    interface OnPurchaseListener {
        fun onPurchaseSuccess(purchase: Purchase?)
        fun onPurchaseFailure(error: Error)
    }

    interface OnQueryProductsListener {
        fun onSuccess(products: List<SkuDetails>)
        fun onFailure(error: Error)
    }

    var onPurchaseListener: OnPurchaseListener? = null

    fun purchase(activity: Activity, product: SkuDetails) {
        onConnected {
            activity.runOnUiThread {
                billingClient.launchBillingFlow(
                    activity,
                    BillingFlowParams.newBuilder().setSkuDetails(product).build()
                )
            }
        }
    }

    fun queryProducts(listener: OnQueryProductsListener, skusList: List<String>) {
        queryProductsForType(
            skusList,
            BillingClient.SkuType.SUBS
        ) { billingResult, skuDetailsList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                listener.onSuccess(skuDetailsList )
            } else {
                listener.onFailure(
                    Error(billingResult.responseCode, billingResult.debugMessage)
                )
            }
        }
    }

    private fun queryProductsForType(
        skusList: List<String>,
        @BillingClient.SkuType type: String,
        listener: SkuDetailsResponseListener
    ) {
        onConnected {
            billingClient.querySkuDetailsAsync(
                SkuDetailsParams.newBuilder().setSkusList(skusList).setType(type).build(),
                listener
            )
        }
    }

    private fun onConnected(block: () -> Unit) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                block()
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchaseList: MutableList<Purchase>?
    ) {
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {

                if (purchaseList == null) {
                    //to be discussed in the next article
                    onPurchaseListener?.onPurchaseSuccess(null)
                    return
                }

                purchaseList.forEach(::processPurchase) //to be declared below
            }
            else -> {
                //error occured or user canceled
                onPurchaseListener?.onPurchaseFailure(
                    BillingClientWrapper.Error(
                        billingResult.responseCode,
                        billingResult.debugMessage
                    )
                )
            }
        }
    }

    private fun processPurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            onPurchaseListener?.onPurchaseSuccess(purchase)

            if (!purchase.isAcknowledged) {
                val prefs = Prefs(context)
                prefs.purchaseToken = purchase.purchaseToken
                acknowledgePurchase(purchase.purchaseToken) { billingResult ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        prefs.purchaseToken = null
                    }
                }
            }
        }
    }

    fun acknowledgePurchase(
        purchaseToken: String,
        callback: AcknowledgePurchaseResponseListener
    ) {
        onConnected {
            billingClient.acknowledgePurchase(
                AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchaseToken)
                    .build(),
                callback::onAcknowledgePurchaseResponse
            )
        }
    }

    class Error(val responseCode: Int, val debugMessage: String)
}