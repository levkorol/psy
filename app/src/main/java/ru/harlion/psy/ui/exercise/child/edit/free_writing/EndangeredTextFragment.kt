package ru.harlion.psy.ui.exercise.child.edit.free_writing


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.animation.addListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import ru.harlion.psy.AppActivity
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEndangeredTextBinding
import ru.harlion.psy.utils.custom_view.EndangeredTextFrameLayout


class EndangeredTextFragment :
    BindingFragment<FragmentEndangeredTextBinding>(FragmentEndangeredTextBinding::inflate) {


    private val viewModel: EditFreeWritingViewModel by viewModels()
    private var animation: ObjectAnimator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = requireArguments().getString("TEXT_FREE_WR")
        val id = requireArguments().getLong("EX_ID")
        binding.textEndangered.text = text

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
            parentFragmentManager.popBackStack()
        }

        binding.textEndangered.setOnClickListener {
            ObjectAnimator.ofFloat(
                binding.root,
                EndangeredTextFrameLayout.ENDANGERED_TEXT_PROGRESS,
                0F,
                1F
            ).apply {
                animation = this
                duration = 3000
                this.addListener(onEnd = {
                    binding.textEnd.text = getString(R.string.text_end_destroy_text)
                    binding.buttonsAndTextEnd.visibility = View.VISIBLE
                    binding.root.setBackgroundResource(R.drawable.pic_bg_12)
                    viewModel.delete(id)
                    animation = null
                })
            }.start()
        }
    }

    override fun onStart() {
        super.onStart()
        val window = (activity as AppActivity).window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.black)
    }

    override fun onDestroyView() {
        animation?.cancel()
        animation = null
        super.onDestroyView()
    }

    override fun onStop() {
        super.onStop()
        val window = (activity as AppActivity).window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.main_dark_violet)
    }

    companion object {
        fun newInstance(text: String, exId: Long) = EndangeredTextFragment().apply {
            arguments = Bundle().apply {
                putString("TEXT_FREE_WR", text)
                putLong("EX_ID", exId)
            }
        }
    }
}