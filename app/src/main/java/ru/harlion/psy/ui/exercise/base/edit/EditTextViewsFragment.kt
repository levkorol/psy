package ru.harlion.psy.ui.exercise.base.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditTextViewsBinding
import ru.harlion.psy.models.TypeEx


class EditTextViewsFragment :
    BindingFragment<FragmentEditTextViewsBinding>(FragmentEditTextViewsBinding::inflate) {

    private val viewModel: EditTextViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val (titleOne, titleTwo, titleThree) = requireArguments().getIntArray("EDIT_TEXT_IDS")!!
        val (hintOne, hintTwo, hintThree) = requireArguments().getIntArray("EDIT_HINTS_IDS")!!
        val (infoOne, infoTwo, infoThree) = requireArguments().getIntArray("EDIT_INFO_IDS")!!

        val typeEx = requireArguments().getSerializable("ENUM")

        when (typeEx) {
            TypeEx.FAIL_DIARY -> {
                binding.questionFor.apply {
                    visibility = View.VISIBLE
                    title = resources.getText(R.string.fail_diary_question_4)
                }
            }
            TypeEx.PERFECT_LIFE -> {
                binding.questionThree.apply {
                    visibility = View.GONE
                }
            }
        }

        binding.apply {

            questionOne.title = resources.getText(titleOne)
            if (hintOne > 0) {
                questionOne.hint = resources.getText(hintOne)
            }
            questionTwo.title = resources.getText(titleTwo)
            when {
                hintTwo > 0 -> {
                    questionTwo.hint = resources.getText(hintTwo)
                }
                titleThree > 0 -> {
                    questionThree.title = resources.getText(titleThree)
                }
                hintThree > 0 -> {
                    questionThree.hint = resources.getText(hintThree)
                }
            }
        }

        binding.save.setOnClickListener {
            when (typeEx) {
                TypeEx.SELF_ESTEEM -> {
                    viewModel.addEx(
                        fieldOne = binding.questionOne.text.toString(),
                        fieldTwo = binding.questionTwo.text.toString(),
                        fieldThree = binding.questionThree.text.toString(),
                        typeEx = TypeEx.SELF_ESTEEM
                    )
                }
                TypeEx.GRATITUDE_DIARY -> {
                    viewModel.addEx(
                        fieldOne = binding.questionOne.text.toString(),
                        fieldTwo = binding.questionTwo.text.toString(),
                        fieldThree = binding.questionThree.text.toString(),
                        typeEx = TypeEx.GRATITUDE_DIARY
                    )
                }
                TypeEx.SUCCESS_DIARY -> {
                    viewModel.addEx(
                        fieldOne = binding.questionOne.text.toString(),
                        fieldTwo = binding.questionTwo.text.toString(),
                        fieldThree = binding.questionThree.text.toString(),
                        typeEx = TypeEx.SUCCESS_DIARY
                    )
                }
                TypeEx.PERFECT_LIFE -> {
                    viewModel.addEx(
                        fieldOne = binding.questionOne.text.toString(),
                        fieldTwo = binding.questionTwo.text.toString(),
                        typeEx = TypeEx.PERFECT_LIFE
                    )
                }
                TypeEx.FAIL_DIARY -> {
                    viewModel.addEx(
                        fieldOne = binding.questionOne.text.toString(),
                        fieldTwo = binding.questionTwo.text.toString(),
                        fieldThree = binding.questionThree.text.toString(),
                        fieldFor = binding.questionFor.text.toString(),
                        typeEx = TypeEx.FAIL_DIARY
                    )
                }
                TypeEx.ACTS_SELF_LOVE -> {
                    viewModel.addEx(
                        fieldOne = binding.questionOne.text.toString(),
                        fieldTwo = binding.questionTwo.text.toString(),
                        fieldThree = binding.questionThree.text.toString(),
                        typeEx = TypeEx.ACTS_SELF_LOVE
                    )
                }
                TypeEx.MY_AMBULANCE -> {
                    viewModel.addEx(
                        fieldOne = binding.questionOne.text.toString(),
                        fieldTwo = binding.questionTwo.text.toString(),
                        fieldThree = binding.questionThree.text.toString(),
                        typeEx = TypeEx.MY_AMBULANCE
                    )
                }
            }
            parentFragmentManager.popBackStack()
        }
    }


    companion object {
        fun newInstance(
            titleOne: Int,
            hintOne: Int = 0,
            infoOne: Int = 0,
            titleTwo: Int,
            hintTwo: Int = 0,
            infoTwo: Int = 0,
            titleThree: Int = 0,
            hintThree: Int = 0,
            infoThree: Int = 0,
            typeEx: TypeEx
        ) = EditTextViewsFragment().apply {
            arguments = Bundle().apply {
                putIntArray("EDIT_TEXT_IDS", intArrayOf(titleOne, titleTwo, titleThree))
                putIntArray("EDIT_HINTS_IDS", intArrayOf(hintOne, hintTwo, hintThree))
                putIntArray("EDIT_INFO_IDS", intArrayOf(infoOne, infoTwo, infoThree))
                putSerializable("ENUM", typeEx)
            }
        }
    }
}
