package ru.harlion.psy.ui.profile.premium


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.google.android.material.snackbar.Snackbar
import ru.harlion.psy.AppApplication
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.data.billing.BillingClientWrapper
import ru.harlion.psy.databinding.FragmentPremiumBinding
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.dialogs.EditTextDialog


class PremiumFragment : BindingFragment<FragmentPremiumBinding>(FragmentPremiumBinding::inflate),
    BillingClientWrapper.OnPurchaseListener {

    lateinit var billingClientWrapper: BillingClientWrapper
    private lateinit var prefs: Prefs

    private val purchaseButtonsMap: Map<String, RelativeLayout> by lazy(LazyThreadSafetyMode.NONE) {
        mapOf(
            "premium_sub_month" to binding.premium,
            //  "premium_sub_year" to binding.premiumYear
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        billingClientWrapper = (requireActivity().application as AppApplication).clientWrapper
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        prefs = Prefs(requireContext())

        billingClientWrapper.onPurchaseListener = this

        displayProducts()

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.premium.setOnClickListener {
           // prefs.isPremiumBilling = true
            Snackbar.make(binding.root, "Подписка в разработке, пока можно воспользоваться промокодом", Snackbar.LENGTH_SHORT).show()
        }

        binding.promoGet.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://t.me/my_psy_app")
            )
            startActivity(browserIntent)
        }

        binding.promo.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                setTitle(getString(R.string.input_promo))
                setEditTextPromo("","")
                setPositiveButton(getString(R.string.yes)) {
                    if (newText.toString() == "google_test"
                        || newText.toString() == "lev_dev_yan") {
                        prefs.isPremiumBilling = true
                        Snackbar.make(binding.root, getString(R.string.promocode_completed), Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(binding.root, getString(R.string.promocode_not_completed), Snackbar.LENGTH_SHORT).show()
                    }
                }
                setNegativeButton(getString(R.string.cancel)) {}
                show()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        billingClientWrapper.onPurchaseListener = null
    }

    override fun onPurchaseSuccess(purchase: Purchase?) {
        if (purchase != null) {
            prefs.isPremiumBilling = true
        }
    }

    override fun onPurchaseFailure(error: BillingClientWrapper.Error) {
        //handle error or user cancelation
    }

    private fun displayProducts() {
        billingClientWrapper.queryProducts(object : BillingClientWrapper.OnQueryProductsListener {
            override fun onSuccess(products: List<SkuDetails>) {
                products.forEach { product ->
                    purchaseButtonsMap[product.sku]?.apply {
                        binding.textPro.text = "${product.description} for ${product.price}"
                        setOnClickListener {
                            billingClientWrapper.purchase(
                                requireActivity(),
                                product
                            ) //will be declared below
                        }
                    }
                }
            }

            override fun onFailure(error: BillingClientWrapper.Error) {
                //handle error
            }
        }, purchaseButtonsMap.keys.toList())
    }
}