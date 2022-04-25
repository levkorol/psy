package ru.harlion.psy.ui.exercise.parent

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentParentExercizesBinding
import ru.harlion.psy.ui.exercise.adapters.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.adapters.MenuEx
import ru.harlion.psy.ui.main.diary_emotions.DiaryEmotionFragment
import ru.harlion.psy.ui.profile.ProfileFragment
import ru.harlion.psy.utils.replaceFragment


class ParentExercizesFragment :
    BindingFragment<FragmentParentExercizesBinding>(FragmentParentExercizesBinding::inflate) {

    private lateinit var adapterMenu: AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercises = listOf(
            MenuEx(getString(R.string.success_diary_ex), R.drawable.menu_trophy, 0),
            MenuEx(getString(R.string.work_with_belief_ex), R.drawable.menu_hands, 2),
            MenuEx(getString(R.string.positive_belief_ex), R.drawable.menu_positive, 0),
            MenuEx(getString(R.string.life_rules_ex), R.drawable.menu_pr, 0),
            MenuEx(getString(R.string.perfect_life_ex), R.drawable.menu_check, 0)
        )

        adapterMenu = AdapterMenuExercizes {
            when (it) {
                0 -> replaceFragment(DiaryEmotionFragment(), true)
                1 -> replaceFragment(DiaryEmotionFragment(), true)
                2 -> replaceFragment(DiaryEmotionFragment(), true)
                3 -> replaceFragment(DiaryEmotionFragment(), true)
                else -> replaceFragment(ProfileFragment(), true)
            }
        }
        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }
        adapterMenu.items = exercises
    }
}