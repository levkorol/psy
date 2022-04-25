package ru.harlion.psy.ui.exercise.adult


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentAdultExercizesBinding
import ru.harlion.psy.ui.exercise.adapters.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.adapters.MenuEx
import ru.harlion.psy.ui.main.diary_emotions.DiaryEmotionFragment
import ru.harlion.psy.ui.profile.ProfileFragment
import ru.harlion.psy.utils.replaceFragment


class AdultExercizesFragment : BindingFragment<FragmentAdultExercizesBinding>(FragmentAdultExercizesBinding::inflate) {

    private lateinit var adapterMenu : AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercises = listOf(
            MenuEx(getString(R.string.self_ex), R.drawable.menu_like, 2),
            MenuEx(getString(R.string.fail_diary_ex), R.drawable.menu_sad, 0),
            MenuEx(getString(R.string.do_love_self_ex), R.drawable.menu_self_love, 0),
            MenuEx(getString(R.string.my_emergency_ex), R.drawable.menu_life_preserver, 0),
            MenuEx(getString(R.string.poll_day_ex), R.drawable.menu_sun, 0)
        )

        adapterMenu = AdapterMenuExercizes {
               when(it) {
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