package ru.harlion.psy.ui.profile.test


import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTestBinding
import ru.harlion.psy.ui.exercise.base.edit.poll_test.EditPollTestFragment
import ru.harlion.psy.utils.replaceFragment


class TestFragment : BindingFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isResult = requireArguments().getBoolean("IS_RESULT")

        if(isResult) {
            val results = requireArguments().getFloatArray("RESULTS")!!
            binding.child.progress = results[0].toInt()
            binding.adult.progress = results[1].toInt()
            binding.parent.progress = results[2].toInt()
            binding.resultLl.visibility = View.VISIBLE
            binding.emptyResult.visibility = View.GONE
        } else {
            binding.resultLl.visibility = View.GONE
            binding.emptyResult.visibility = View.VISIBLE
        }

        initClicks()
    }

    private fun initClicks() {
        binding.testBegin.setOnClickListener {
            replaceFragment(EditPollTestFragment.newInstance(0,true), true)
        }
    }

    companion object {
        fun newInstance(
            isResult: Boolean,
            results : FloatArray) = TestFragment().apply {
            arguments = Bundle().apply {
               putFloatArray("RESULTS", results)
               putBoolean("IS_RESULT", isResult)
            }
        }
    }
}