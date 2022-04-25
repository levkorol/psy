package ru.harlion.psy.ui.exercise.child


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentChildExercizesBinding
import ru.harlion.psy.ui.exercise.adapters.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.adapters.MenuEx
import ru.harlion.psy.ui.exercise.edit.EditExTextRecyclerFragment
import ru.harlion.psy.ui.main.diary_emotions.DiaryEmotionFragment
import ru.harlion.psy.ui.profile.ProfileFragment
import ru.harlion.psy.utils.replaceFragment


class ChildExercizesFragment : BindingFragment<FragmentChildExercizesBinding>(
    FragmentChildExercizesBinding::inflate) {

    private lateinit var adapterMenu : AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercises = listOf(
            MenuEx(getString(R.string.thanks_diary), R.drawable.menu_heart, 2),
            MenuEx(getString(R.string.wish_diary_ex), R.drawable.menu_star, 0),
            MenuEx(getString(R.string.free_writing_ex), R.drawable.menu_freewriting, 0),
            MenuEx(getString(R.string.ideas_diary_ex), R.drawable.menu_idea, 0),
            MenuEx(getString(R.string.album_ex), R.drawable.menu_moments, 0)
        )

        adapterMenu = AdapterMenuExercizes {
            when(it) {
                0 -> replaceFragment(EditExTextRecyclerFragment(), true)
                1 -> replaceFragment(EditExTextRecyclerFragment(), true)
                2 -> replaceFragment(EditExTextRecyclerFragment(), true)
                3 -> replaceFragment(EditExTextRecyclerFragment(), true)
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