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

        initClicks()
    }

    private fun initClicks() {
        binding.testBegin.setOnClickListener {
            replaceFragment(EditPollTestFragment(), true)
        }
    }
}