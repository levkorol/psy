package ru.harlion.psy.ui.exercise.child.meditation


import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentExMeditationBinding


class ExMeditationFragment :
    BindingFragment<FragmentExMeditationBinding>(FragmentExMeditationBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (requireArguments().getSerializable("type_meditation")) {
            TypeMeditation.ANTI_STRESS -> {

            }
            TypeMeditation.GOOD_NIGHT -> {

            }
            TypeMeditation.RECTANGLE -> {

            }
            TypeMeditation.RELAXED -> {

            }
        }

        binding.play.setOnClickListener {
            // todo
        }
    }

    companion object {
        fun newInstance(typeMeditation: TypeMeditation) = ExMeditationFragment().apply {
            arguments = Bundle().apply {
                putSerializable("type_meditation", typeMeditation)
            }
        }
    }
}

enum class TypeMeditation {
    ANTI_STRESS,
    GOOD_NIGHT,
    RECTANGLE,
    RELAXED
}