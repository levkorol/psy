package ru.harlion.psy.ui.exercise.child


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentChildExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.ExListFragment
import ru.harlion.psy.ui.exercise.adapters.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.adapters.MenuEx
import ru.harlion.psy.utils.replaceFragment


class ChildExercizesFragment : BindingFragment<FragmentChildExercizesBinding>(
    FragmentChildExercizesBinding::inflate
) {
    private lateinit var adapterMenu: AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val exercises = listOf(
            MenuEx(getString(R.string.thanks_diary), R.drawable.menu_heart, 2),
            MenuEx(getString(R.string.wish_diary_ex), R.drawable.menu_star, 0),
            MenuEx(getString(R.string.free_writing_ex), R.drawable.menu_freewriting, 0),
            MenuEx(getString(R.string.ideas_diary_ex), R.drawable.menu_idea, 0),
            MenuEx(getString(R.string.album_ex), R.drawable.menu_moments, 0)
        )

        adapterMenu = AdapterMenuExercizes {
            when (it) {
                0 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.thanks_diary,
                        R.string.full_text_info_gratitude_diary,
                        TypeEx.GRATITUDE_DIARY
                    ), true
                )
                1 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.wish_diary_ex,
                        R.string.full_text_info_wish_diary,
                        TypeEx.WISH_DIARY
                    ), true
                )
                2 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.free_writing_ex,
                        R.string.full_text_info_free_writing,
                        TypeEx.FREE_WRITING
                    ), true
                )
                3 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.ideas_diary_ex,
                        R.string.full_text_info_ideas_diary,
                        TypeEx.IDEAS_DIARY
                    ), true
                )
                else -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.album_ex,
                        R.string.full_text_info_album,
                        TypeEx.HIGHLIGHTS_ALBUM
                    ), true
                )
            }
        }
        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }
        adapterMenu.items = exercises
    }
}