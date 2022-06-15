package ru.harlion.psy.ui.profile.test


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ru.harlion.psy.R
import ru.harlion.psy.app
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTestBinding
import ru.harlion.psy.ui.exercise.base.edit.poll_test.EditPollTestFragment
import ru.harlion.psy.utils.replaceFragment


class TestFragment : BindingFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private lateinit var results: FloatArray

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isResult = requireArguments().getBoolean("IS_RESULT")

        if (isResult) {
            results = requireArguments().getFloatArray("RESULTS")!!
            binding.child.progress = results[0].toInt()
            binding.adult.progress = results[1].toInt()
            binding.parent.progress = results[2].toInt()

            binding.countProcentChild.text = "${results[0].toInt()} %"
            binding.countProcentAdult.text = "${results[1].toInt()} %"
            binding.countProcentParent.text = "${results[2].toInt()} %"
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
            replaceFragment(EditPollTestFragment.newInstance(0, true), true)
        }

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.saveProgress.setOnClickListener {
            app.user.value = app.user.value?.copy(
                progressChild = results[0].toInt(),
                progressAdult = results[1].toInt(),
                progressParent = results[2].toInt(),
            )
            Snackbar.make(binding.root, getString(R.string.save_progress), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        fun newInstance(
            isResult: Boolean,
            results: FloatArray
        ) = TestFragment().apply {
            arguments = Bundle().apply {
                putFloatArray("RESULTS", results)
                putBoolean("IS_RESULT", isResult)
            }
        }
    }
}