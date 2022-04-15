package ru.harlion.psy.ui.exercise.child


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentChildExercizesBinding
import ru.harlion.psy.ui.exercise.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.MenuEx


class ChildExercizesFragment : BindingFragment<FragmentChildExercizesBinding>(
    FragmentChildExercizesBinding::inflate) {


    private lateinit var adapterMenu : AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterMenu = AdapterMenuExercizes()
        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }

        adapterMenu.items = listOf(
            MenuEx("Дневник благодарностей", R.drawable.menu_heart,2),
            MenuEx("Дневник желаний", R.drawable.menu_star,0),
            MenuEx("Фрирайтинг", R.drawable.menu_freewriting,0),
            MenuEx("Дневник идей", R.drawable.menu_idea,0),
            MenuEx("Альбом ярких моментов", R.drawable.menu_moments,0)
        )
    }
}