package ru.harlion.psy.ui.exercise.child.edit.free_writing

import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEndangeredTextBinding


class EndangeredTextFragment :
    BindingFragment<FragmentEndangeredTextBinding>(FragmentEndangeredTextBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.text.setOnClickListener {

        }
    }

}