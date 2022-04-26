package ru.harlion.psy.ui.exercise.edit


import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditExTextRecyclerBinding


class EditExTextRecyclerFragment :
    BindingFragment<FragmentEditExTextRecyclerBinding>(FragmentEditExTextRecyclerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val (title, hint, listHint) = requireArguments().getIntArray("TEXT_IDS")!!

        binding.questionOne.title = resources.getText(title)
        binding.questionOne.hint = resources.getText(hint)
        binding.answers.hint = resources.getText(listHint)

    }

    companion object {
        fun newInstance(title : Int, hint : Int , listHint : Int) = EditExTextRecyclerFragment().apply {
            arguments = Bundle().apply {
                putIntArray("TEXT_IDS", intArrayOf(title, hint, listHint))
            }
        }
    }
}