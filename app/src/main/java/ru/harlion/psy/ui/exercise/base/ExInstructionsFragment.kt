package ru.harlion.psy.ui.exercise.base

import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentExInstructionsBinding


class ExInstructionsFragment :
    BindingFragment<FragmentExInstructionsBinding>(FragmentExInstructionsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val (oneTitle,
            twoTitle,
            threeTitle,
            forTitle,
            fifeTitle) = requireArguments().getIntArray("TEXT_IDS")!!

    }

    companion object {
        fun newInstance(
            oneTitle: Int,
            twoTitle: Int,
            threeTitle: Int,
            forTitle: Int,
            fifeTitle: Int,
        ) = ExInstructionsFragment().apply {
            arguments = Bundle().apply {
                putIntArray(
                    "TEXT_IDS",
                    intArrayOf(oneTitle, twoTitle, threeTitle, forTitle, fifeTitle)
                )
            }
        }
    }
}