package ru.harlion.psy.ui.profile.pincode


import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.bitfactory.pincodelayout.PinCodeActions
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentSetPinCodeBinding
import ru.harlion.psy.ui.main.MainFragment
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.replaceFragment


class SetPinCodeFragment :
    BindingFragment<FragmentSetPinCodeBinding>(FragmentSetPinCodeBinding::inflate) {

    private lateinit var pref: Prefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Prefs(requireContext())

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.switchIsPassword.isChecked = pref.isPassword
        binding.switchIsPassword.setOnClickListener {
            if (pref.password != "") {
                if (!pref.isPassword) {
                    binding.switchIsPassword.isChecked = true
                    pref.isPassword = true
                } else {
                    binding.switchIsPassword.isChecked = false
                    pref.isPassword = false
                }
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.empty_password_saved),
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.switchIsPassword.isChecked = false
            }
        }

        binding.showPassword.setOnClickListener {
            Snackbar.make(
                binding.root,
                if (pref.password != "") pref.password else getString(R.string.empty_password_saved),
                Snackbar.LENGTH_SHORT
            ).show()
        }

        val callback: PinCodeActions = object : PinCodeActions {
            override fun onPinEntered(pin: String) {
                pref.password = pin
                Snackbar.make(
                    binding.root,
                    getString(R.string.update_pin_code),
                    Snackbar.LENGTH_SHORT
                ).show()
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