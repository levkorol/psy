package ru.harlion.psy.ui.profile.pincode


import android.R
import android.os.Bundle
import android.view.View
import com.kevalpatel.passcodeview.PinView
import com.kevalpatel.passcodeview.authenticator.PasscodeViewPinAuthenticator
import com.kevalpatel.passcodeview.interfaces.AuthenticationListener
import com.kevalpatel.passcodeview.keys.KeyNamesBuilder
import com.kevalpatel.passcodeview.keys.RoundKey
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentPinCodeBinding


class PinCodeFragment : BindingFragment<FragmentPinCodeBinding>(FragmentPinCodeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pinView.apply {
            val correctPin = intArrayOf(1, 2, 3, 4)
            pinAuthenticator = PasscodeViewPinAuthenticator(correctPin)
            pinLength = PinView.DYNAMIC_PIN_LENGTH

            setAuthenticationListener( object : AuthenticationListener {
                override fun onAuthenticationSuccessful() {

                }

                override fun onAuthenticationFailed() {

                }

            }

            )

            setKey(RoundKey.Builder(binding.pinView)
               // .setKeyPadding(R.dimen)
                .setKeyStrokeColorResource(R.color.darker_gray)
              //  .setKeyStrokeWidth(R.dimen.dialog_min_width_major)
                .setKeyTextColorResource(R.color.background_dark))
 //               .setKeyTextSize(R.dimen.app_icon_size))

//            binding.pinView.setIndicator(
//                Builder(pinView)
//                    .setIndicatorRadius(R.dimen.indicator_radius)
//                    .setIndicatorFilledColorResource(R.color.colorAccent)
//                    .setIndicatorStrokeColorResource(R.color.colorAccent)
//                    .setIndicatorStrokeWidth(R.dimen.indicator_stroke_width)
//            )

//            binding.pinView.setKeyNames(
//                KeyNamesBuilder()
//                    .setKeyOne(this, R.string.key_1)
//                    .setKeyTwo(this, R.string.key_2)
//                    .setKeyThree(this, R.string.key_3)
//                    .setKeyFour(this, R.string.key_4)
//                    .setKeyFive(this, R.string.key_5)
//                    .setKeySix(this, R.string.key_6)
//                    .setKeySeven(this, R.string.key_7)
//                    .setKeyEight(this, R.string.key_8)
//                    .setKeyNine(this, R.string.key_9)
//                    .setKeyZero(this, R.string.key_0)
//            )

        }



    }
}