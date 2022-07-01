package ru.harlion.psy.ui.exercise.child.meditation


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentMeditationBinding
import ru.harlion.psy.utils.replaceFragment

class MeditationFragment : BindingFragment<FragmentMeditationBinding>(FragmentMeditationBinding::inflate) {

    private lateinit var adapterMeditation: AdapterMeditation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterMeditation = AdapterMeditation {
            when(it) {
                0 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.ANTI_STRESS), true)
                1 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.GOOD_NIGHT), true)
                2 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.RELAXED), true)
                3 -> replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.RECTANGLE), true)
            }
        }
        binding.meditations.apply {
            adapter = adapterMeditation
            layoutManager = GridLayoutManager(requireContext(),2)
        }

        adapterMeditation.items = listOf(
            ItemMeditation("Антистресс", R.drawable.pick_bg_2),
            ItemMeditation("Доброй ночи", R.drawable.pick_bg_10),
            ItemMeditation("Спокойное дыхание", R.drawable.pick_bg_1),
            ItemMeditation("Квадрат", R.drawable.pick_bg_2),
        )
    }
}