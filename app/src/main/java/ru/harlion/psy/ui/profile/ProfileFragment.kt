package ru.harlion.psy.ui.profile


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentProfileBinding
import ru.harlion.psy.ui.main.diary_emotions.DiaryEmotionFragment
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.TableEmotionsFragment
import ru.harlion.psy.ui.profile.pincode.PinCodeFragment
import ru.harlion.psy.ui.profile.premium.PremiumFragment
import ru.harlion.psy.utils.replaceFragment


class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()
    }

    private fun initClicks() {
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }
        binding.diaryEmotions.setOnClickListener {
            replaceFragment(DiaryEmotionFragment(), true)
        }
        binding.premium.setOnClickListener {
            replaceFragment(PremiumFragment(), true)
        }
        binding.pinCode.setOnClickListener {
            replaceFragment(PinCodeFragment(), true)
        }
        binding.mail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:lev.dev.apps@gmail.com")
            }
            startActivity(Intent.createChooser(emailIntent, getString(R.string.mail_to)))
        }
        binding.mark.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=ru.harlion.pomodorolist")
            )
            startActivity(browserIntent)
        }
        binding.infoUser.setOnClickListener { }
        binding.psyProject.setOnClickListener { }
    }
}