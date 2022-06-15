package ru.harlion.psy.ui.profile.pincode


import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.bitfactory.pincodelayout.PinCodeActions
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentPinCodeBinding
import ru.harlion.psy.ui.main.MainFragment
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.replaceFragment


class PinCodeFragment : BindingFragment<FragmentPinCodeBinding>(FragmentPinCodeBinding::inflate) {

    private lateinit var pref : Prefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Prefs(requireContext())

        val callback: PinCodeActions = object : PinCodeActions {
            override fun onPinEntered(pin: String) {
                if(pref.password == pin) {
                    replaceFragment(MainFragment(), false)
                } else {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.error_pin_code),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    onPinCleared()
                }
            }

            override fun onPinCleared() {
                // Called when the pin is cleared/empty
            }

            override fun onPinFilled() {
                // Called when the pin is entered and the View looses focus
            }
        }

        binding.pinCode.setCallback(callback)
    }
}