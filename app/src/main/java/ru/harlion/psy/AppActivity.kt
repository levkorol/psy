package ru.harlion.psy


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.harlion.psy.ui.main.MainFragment
import ru.harlion.psy.ui.profile.on_boarding.OnBoardingFragment
import ru.harlion.psy.ui.profile.pincode.PinCodeFragment
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.replaceFragment

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.VioletAndWhiteTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = Prefs(this)

        if (!prefs.isShowOnBoarding) {
            prefs.isShowOnBoarding = true
            replaceFragment(OnBoardingFragment(), false)
        } else {
             replaceFragment( MainFragment(), false)
            //  replaceFragment(PinCodeFragment(), false)
        }
    }
}