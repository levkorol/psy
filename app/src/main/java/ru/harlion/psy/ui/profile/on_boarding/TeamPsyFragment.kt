package ru.harlion.psy.ui.profile.on_boarding


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTeamPsyBinding
import ru.harlion.psy.utils.setImageRes

class TeamPsyFragment : BindingFragment<FragmentTeamPsyBinding>(FragmentTeamPsyBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photoPsy.setImageRes(R.drawable.photo_psy)

        binding.siteOne.apply {
            text = "https://dzening.ru/"
            setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://dzening.ru/")
                )
                startActivity(browserIntent)
            }
        }

        binding.siteTwo.apply {
            text = "https://www.b17.ru/dzinamur/"
            setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.b17.ru/dzinamur/")
                )
                startActivity(browserIntent)
            }
        }
    }

}