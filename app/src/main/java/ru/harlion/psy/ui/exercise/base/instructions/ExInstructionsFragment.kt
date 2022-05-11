package ru.harlion.psy.ui.exercise.base.instructions

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
            forTitle) = requireArguments().getIntArray("TEXT_IDS")!!

        binding.aboutTv.text = resources.getText(oneTitle)
        binding.intervalTv.text = resources.getText(twoTitle)
        if(threeTitle > 0) {
            binding.rulesTv.visibility = View.VISIBLE
            binding.titleRules.visibility = View.VISIBLE
            binding.rulesTv.text = resources.getText(threeTitle)
        }

        binding.resultTv.text = resources.getText(forTitle)

    }

    companion object {
        fun newInstance(
            oneTitle: Int,
            twoTitle: Int = 0,
            threeTitle: Int = 0,
            forTitle: Int = 0
        ) = ExInstructionsFragment().apply {
            arguments = Bundle().apply {
                putIntArray(
                    "TEXT_IDS",
                    intArrayOf(oneTitle, twoTitle, threeTitle, forTitle)
                )
            }
        }
    }
}