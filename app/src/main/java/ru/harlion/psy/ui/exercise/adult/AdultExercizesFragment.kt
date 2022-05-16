package ru.harlion.psy.ui.exercise.adult


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.AppApplication
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentAdultExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.ex_list.ExListFragment
import ru.harlion.psy.ui.exercise.base.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.base.MenuEx
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.ui.main.my_day.DayPollFragment
import ru.harlion.psy.utils.dialogs.EditTextDialog
import ru.harlion.psy.utils.replaceFragment


class AdultExercizesFragment :
    BindingFragment<FragmentAdultExercizesBinding>(FragmentAdultExercizesBinding::inflate) {

    private lateinit var adapterMenu: AdapterMenuExercizes
    private val app = AppApplication()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()

        app.user.observe(viewLifecycleOwner, {
            binding.name.text = it.nameAdult
        })

        val exercises = listOf(
            MenuEx(getString(R.string.self_ex), R.drawable.menu_like, 2),
            MenuEx(getString(R.string.fail_diary_ex), R.drawable.menu_sad, 0),
            MenuEx(getString(R.string.do_love_self_ex), R.drawable.menu_self_love, 0),
            MenuEx(getString(R.string.my_emergency_ex), R.drawable.menu_life_preserver, 0),
            //  MenuEx(getString(R.string.poll_day_ex), R.drawable.menu_sun, 0)
        )

        adapterMenu = AdapterMenuExercizes {
            when (it) {
                0 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.self_ex,
                        TypeEx.SELF_ESTEEM,
                    ), true
                )
                1 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.fail_diary_ex,
                        TypeEx.FAIL_DIARY
                    ), true
                )
                2 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.do_love_self_ex,
                        TypeEx.ACTS_SELF_LOVE
                    ), true
                )
                else -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.my_emergency_ex,
                        TypeEx.MY_AMBULANCE
                    ), true
                )
                //  else -> replaceFragment(DayPollFragment(), true)
            }
        }

        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }
        adapterMenu.items = exercises
    }

    private fun initClick() {
        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.info.setOnClickListener {
            replaceFragment(
                ExInstructionsFragment.newInstance(
                    oneTitle = R.string.adult_info,
                    toolbar = R.string.informations,
                    isEx = false
                ), true
            )
        }

        binding.edit.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                val text = setEditText(getString(R.string.name_adult))
                setTitle(getString(R.string.name_adult_title))
                setPositiveButton(getString(R.string.save)) {
                    val name = text.findViewById<TextView>(R.id.input_text).text
                    app.user.value?.name = name.toString()
                    binding.name.text = name.toString()
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }
    }
}