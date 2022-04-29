package ru.harlion.psy.ui.exercise.child.edit.free_writing


import android.os.Bundle
import android.view.View
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditFreeWritingBinding


class EditFreeWritingFragment :
    BindingFragment<FragmentEditFreeWritingBinding>(FragmentEditFreeWritingBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.myRequest.title = resources.getText(R.string.ex_free_writing_qwestion_one)

        binding.btnBeginEx.setOnClickListener {
            binding.stepOne.visibility = View.GONE
            binding.stepTwo.visibility = View.VISIBLE

        }

    }

}