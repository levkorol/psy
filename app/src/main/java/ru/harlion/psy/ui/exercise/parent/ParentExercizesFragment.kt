package ru.harlion.psy.ui.exercise.parent

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentParentExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.ExListFragment
import ru.harlion.psy.ui.exercise.base.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.base.MenuEx
import ru.harlion.psy.utils.replaceFragment


class ParentExercizesFragment :
    BindingFragment<FragmentParentExercizesBinding>(FragmentParentExercizesBinding::inflate) {

    private lateinit var adapterMenu: AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val exercises = listOf(
            MenuEx(getString(R.string.success_diary_ex), R.drawable.menu_trophy, 0),
            MenuEx(getString(R.string.work_with_belief_ex), R.drawable.menu_hands, 2),
            MenuEx(getString(R.string.positive_belief_ex), R.drawable.menu_positive, 0),
            MenuEx(getString(R.string.life_rules_ex), R.drawable.menu_pr, 0),
            MenuEx(getString(R.string.perfect_life_ex), R.drawable.menu_check, 0)
        )

        adapterMenu = AdapterMenuExercizes {
            when (it) {
                0 -> replaceFragment( ExListFragment.newInstance(
                    R.string.success_diary_ex,
                    R.string.full_text_info_gratitude_diary,
                    TypeEx.SUCCESS_DIARY
                ), true)
                1 -> replaceFragment( ExListFragment.newInstance(
                    R.string.work_with_belief_ex,
                    R.string.full_text_info_gratitude_diary,
                    TypeEx.WORK_WITH_BELIEFS
                ), true)
                2 -> replaceFragment( ExListFragment.newInstance(
                    R.string.positive_belief_ex,
                    R.string.full_text_info_gratitude_diary,
                    TypeEx.POSITIVE_BELIEFS
                ), true)
                3 -> replaceFragment( ExListFragment.newInstance(
                    R.string.life_rules_ex,
                    R.string.full_text_info_gratitude_diary,
                    TypeEx.LIFE_RULES
                ), true)
                else -> replaceFragment( ExListFragment.newInstance(
                    R.string.perfect_life_ex,
                    R.string.full_text_info_gratitude_diary,
                    TypeEx.PERFECT_LIFE
                ), true)
            }
        }
        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }
        adapterMenu.items = exercises
    }
}