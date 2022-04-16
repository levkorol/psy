package ru.harlion.psy.ui.profile


import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentProfileBinding
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.TableEmotionsFragment
import ru.harlion.psy.utils.replaceFragment


class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()
    }

    private fun initClicks() {
        binding.tableEmotions.setOnClickListener {
            replaceFragment(TableEmotionsFragment(), false)
        }
    }
}