package ru.harlion.psy.ui.exercise.child.meditation


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentMeditationBinding
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.utils.replaceFragment

class MeditationFragment :
    BindingFragment<FragmentMeditationBinding>(FragmentMeditationBinding::inflate) {

    private lateinit var adapterMeditation: AdapterMeditation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        adapterMeditation = AdapterMeditation({
            when (it) {
                0 -> replaceFragment(
                    ExMeditationFragment.newInstance(TypeMeditation.ANTI_STRESS),
                    true
                )
                1 -> Snackbar.make(
                    binding.root,
                    "Будет доступна в следующих версиях",
                    Snackbar.LENGTH_SHORT
                ).show()
//                    replaceFragment(
//                    ExMeditationFragment.newInstance(TypeMeditation.GOOD_MORNING),
//                    true
                //  )
                2 -> Snackbar.make(
                    binding.root,
                    "Будет доступна в следующих версиях",
                    Snackbar.LENGTH_SHORT
                ).show()
//                    replaceFragment(
//                    ExMeditationFragment.newInstance(TypeMeditation.GOOD_NIGHT),
//                    true
//                )
                3 -> Snackbar.make(
                    binding.root,
                    "Будет доступна в следующих версиях",
                    Snackbar.LENGTH_SHORT
                ).show()
                //replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.RELAXED), true)
                4 -> Snackbar.make(
                    binding.root,
                    "Будет доступна в следующих версиях",
                    Snackbar.LENGTH_SHORT
                ).show()
//                    replaceFragment(
//                    ExMeditationFragment.newInstance(TypeMeditation.RECTANGLE),
//                    true
//                )
            }
        }, {
            when (it) {
                0 -> replaceFragment(
                    ExInstructionsFragment.newInstance(
                        R.string.meditation_anti_stress_info_one,
                        R.string.meditation_anti_stress_info_two,
                        R.string.meditation_anti_stress_info_three,
                        R.string.meditation_anti_stress_info_for,
                        R.string.mditation_anti_stress
                    ), true
                )
                1 -> replaceFragment(
                    ExInstructionsFragment.newInstance(
                        R.string.meditation_good_morning_info_one,
                        R.string.meditation_good_morning_info_two,
                        R.string.meditation_good_morning_info_three,
                        R.string.meditation_good_morning_info_for,
                        R.string.meditation_good_morning
                    ),
                    true
                )
                2 -> replaceFragment(
                    ExInstructionsFragment.newInstance(
                        R.string.meditation_good_night_info_one,
                        R.string.meditation_good_night_info_two,
                        R.string.meditation_good_night_info_three,
                        R.string.meditation_good_night_info_for,
                        R.string.meditation_good_night
                    ),
                    true
                )
                3 -> replaceFragment(
                    ExInstructionsFragment.newInstance(
                        R.string.meditation_relax_info_one,
                        R.string.meditation_relax_info_two,
                        R.string.meditation_relax_info_three,
                        R.string.meditation_relax_info_for,
                        R.string.meditation_relax
                    ),
                    true
                )
                4 -> replaceFragment(
                    ExInstructionsFragment.newInstance(
                        R.string.meditation_rectangle_info_one,
                        R.string.meditation_rectangle_info_two,
                        R.string.meditation_rectangle_info_three,
                        R.string.meditation_rectangle_info_for,
                        R.string.meditation_rectangle
                    ),
                    true
                )
            }
        })

        binding.meditations.apply {
            adapter = adapterMeditation
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        adapterMeditation.items = listOf(
            ItemMeditation(
                getString(R.string.mditation_anti_stress),
                R.color.meditation_anti_stress,
                R.drawable.ic_smile_beam
            ),
            ItemMeditation(
                getString(R.string.meditation_good_morning),
                R.color.meditation_good_morning,
                R.drawable.ic_sun
            ),
            ItemMeditation(
                getString(R.string.meditation_good_night),
                R.color.meditation_good_night,
                R.drawable.ic_moon_stars
            ),
            ItemMeditation(
                getString(R.string.meditation_relax),
                R.color.meditation_relax,
                R.drawable.ic_flower_tulip
            ),
            ItemMeditation(
                getString(R.string.meditation_rectangle),
                R.color.meditation_rectangle,
                R.drawable.ic_square
            ),
        )
    }
}