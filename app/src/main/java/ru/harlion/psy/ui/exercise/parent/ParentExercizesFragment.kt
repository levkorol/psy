package ru.harlion.psy.ui.exercise.parent

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentParentExercizesBinding
import ru.harlion.psy.ui.exercise.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.MenuEx


class ParentExercizesFragment :
    BindingFragment<FragmentParentExercizesBinding>(FragmentParentExercizesBinding::inflate) {

    private lateinit var adapterMenu : AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterMenu = AdapterMenuExercizes()
        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }

        adapterMenu.items = listOf(
            MenuEx("Дневник успеха", R.drawable.menu_trophy,0),
            MenuEx("Работа с убеждениями", R.drawable.menu_hands,2),
            MenuEx("Позитивные утверждения", R.drawable.menu_positive,0),
            MenuEx("Правила жизни", R.drawable.menu_pr,0),
            MenuEx("Идеальная жизнь", R.drawable.menu_check,0)
        )
    }

}