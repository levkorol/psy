package ru.harlion.psy.ui.exercise.adult


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentAdultExercizesBinding
import ru.harlion.psy.ui.exercise.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.MenuEx


class AdultExercizesFragment : BindingFragment<FragmentAdultExercizesBinding>(FragmentAdultExercizesBinding::inflate) {

    private lateinit var adapterMenu : AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterMenu = AdapterMenuExercizes()
        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }

        adapterMenu.items = listOf(
            MenuEx("Самооценка", R.drawable.menu_like,2),
            MenuEx("Дневник НЕуспеха", R.drawable.menu_sad,0),
            MenuEx("Действия любви к себе", R.drawable.menu_self_love,0),
            MenuEx("Моя скорая помощь", R.drawable.menu_life_preserver,0),
            MenuEx("Опрос дня", R.drawable.menu_sun,0)
        )
    }
}