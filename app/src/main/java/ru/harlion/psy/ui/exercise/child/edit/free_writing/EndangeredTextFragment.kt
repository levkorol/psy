package ru.harlion.psy.ui.exercise.child.edit.free_writing

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEndangeredTextBinding
import ru.harlion.psy.utils.EndangeredTextFrameLayout


class EndangeredTextFragment :
    BindingFragment<FragmentEndangeredTextBinding>(FragmentEndangeredTextBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.text.setOnClickListener {
            ObjectAnimator.ofFloat(
                binding.root,
                EndangeredTextFrameLayout.ENDANGERED_TEXT_PROGRESS,
                0F,
                1F
            ).apply {
                duration = 3000
            }.start()
        }
    }
}