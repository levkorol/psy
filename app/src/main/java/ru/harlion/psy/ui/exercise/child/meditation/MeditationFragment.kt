package ru.harlion.psy.ui.exercise.child.meditation


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentMeditationBinding
import ru.harlion.psy.utils.replaceFragment

class MeditationFragment : BindingFragment<FragmentMeditationBinding>(FragmentMeditationBinding::inflate) {

    private lateinit var adapterMeditation: AdapterMeditation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        adapterMeditation = AdapterMeditation {
            when(it) {
                0 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.ANTI_STRESS), true)
                1 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.GOOD_MORNING), true)
                2 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.GOOD_NIGHT), true)
                3 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.RELAXED), true)
                4 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.RECTANGLE), true)
            }
        }
        binding.meditations.apply {
            adapter = adapterMeditation
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        adapterMeditation.items = listOf(
            ItemMeditation("Антистресс", R.color.meditation_anti_stress),
            ItemMeditation("Доброго утра", R.color.meditation_good_morning),
            ItemMeditation("Доброй ночи", R.color.meditation_good_night),
            ItemMeditation("Спокойное дыхание", R.color.meditation_relax),
            ItemMeditation("Квадрат", R.color.meditation_rectangle),
        )
    }
}