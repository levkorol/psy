package ru.harlion.psy.ui.exercise.adult


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentAdultExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.ex_list.ExListFragment
import ru.harlion.psy.ui.exercise.base.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.base.MenuEx
import ru.harlion.psy.ui.main.my_day.DayPollFragment
import ru.harlion.psy.utils.replaceFragment


class AdultExercizesFragment : BindingFragment<FragmentAdultExercizesBinding>(FragmentAdultExercizesBinding::inflate) {

    private lateinit var adapterMenu : AdapterMenuExercizes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val exercises = listOf(
            MenuEx(getString(R.string.self_ex), R.drawable.menu_like, 2),
            MenuEx(getString(R.string.fail_diary_ex), R.drawable.menu_sad, 0),
            MenuEx(getString(R.string.do_love_self_ex), R.drawable.menu_self_love, 0),
            MenuEx(getString(R.string.my_emergency_ex), R.drawable.menu_life_preserver, 0),
            MenuEx(getString(R.string.poll_day_ex), R.drawable.menu_sun, 0)
        )

        adapterMenu = AdapterMenuExercizes {
               when(it) {
                    0 -> replaceFragment( ExListFragment.newInstance(
                        R.string.self_ex,
                        R.string.full_text_info_self_esteem,
                        TypeEx.SELF_ESTEEM
                    ), true)
                    1 -> replaceFragment( ExListFragment.newInstance(
                        R.string.fail_diary_ex,
                        R.string.full_text_info_fail_diary,
                        TypeEx.FAIL_DIARY
                    ), true)
                    2 -> replaceFragment( ExListFragment.newInstance(
                        R.string.do_love_self_ex,
                        R.string.full_text_info_self_love,
                        TypeEx.ACTS_SELF_LOVE
                    ), true)
                    3 -> replaceFragment( ExListFragment.newInstance(
                        R.string.my_emergency_ex,
                        R.string.full_text_info_my_ambulance,
                        TypeEx.MY_AMBULANCE
                    ), true)
                    else -> replaceFragment(DayPollFragment(), true) //todo ?
               }
        }

        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }
        adapterMenu.items = exercises
    }
}