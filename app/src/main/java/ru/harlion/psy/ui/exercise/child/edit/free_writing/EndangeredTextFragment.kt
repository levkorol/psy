package ru.harlion.psy.ui.exercise.child.edit.free_writing

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import ru.harlion.psy.AppActivity
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEndangeredTextBinding
import ru.harlion.psy.utils.EndangeredTextFrameLayout


class EndangeredTextFragment :
    BindingFragment<FragmentEndangeredTextBinding>(FragmentEndangeredTextBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = requireArguments().getString("TEXT_FREE_WR")
        binding.textEndangered.text = text

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
           // parentFragmentManager.popBackStack() //todo
        }

        binding.textEndangered.setOnClickListener {
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

    override fun onStart() {
        super.onStart()
        val window = (activity as AppActivity).window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.black)
    }

    override fun onStop() {
        super.onStop()
        val window = (activity as AppActivity).window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.main_dark_violet)
    }

    companion object {
        fun newInstance(text: String) = EndangeredTextFragment().apply {
            arguments = Bundle().apply {
                putString("TEXT_FREE_WR", text)
            }
        }
    }
}