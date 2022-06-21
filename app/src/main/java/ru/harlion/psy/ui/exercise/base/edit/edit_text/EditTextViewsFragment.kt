package ru.harlion.psy.ui.exercise.base.edit.edit_text

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditTextViewsBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.utils.dialogs.EditTextDialog


class EditTextViewsFragment :
    BindingFragment<FragmentEditTextViewsBinding>(FragmentEditTextViewsBinding::inflate) {

    private val viewModel: EditTextViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getLong("ID")
        val typeEx = requireArguments().getSerializable("ENUM")
        val (titleOne, titleTwo, titleThree) = requireArguments().getIntArray("EDIT_TEXT_IDS")!!
        val (hintOne, hintTwo, hintThree) = requireArguments().getIntArray("EDIT_HINTS_IDS")!!
        val (infoOne, infoTwo, infoThree) = requireArguments().getIntArray("EDIT_INFO_IDS")!!

        viewModel.getExById(id)
        observe()

        when (typeEx) {
            TypeEx.FAIL_DIARY -> {
                binding.questionFor.apply {
                    visibility = View.VISIBLE
                    title = resources.getText(R.string.fail_diary_question_4)
                    binding.questionOne.textInfo = resources.getText(infoOne)
                }
            }
            TypeEx.PERFECT_LIFE -> {
                binding.questionThree.apply {
                    visibility = View.GONE
                }
                binding.questionOne.lines = 8
                binding.questionTwo.lines = 8
            }
        }
        binding.apply {

            questionOne.title = resources.getText(titleOne)
            questionTwo.title = resources.getText(titleTwo)

            if (hintOne > 0) {
                questionOne.hint = resources.getText(hintOne)
            }
            if (hintTwo > 0) {
                questionTwo.hint = resources.getText(hintTwo)
            }
            if (titleThree > 0) {
                questionThree.title = resources.getText(titleThree)
            }
            if (hintThree > 0) {
                questionThree.hint = resources.getText(hintThree)
            }
        }
        if (id > 0) {
            binding.delete.visibility = View.VISIBLE
            binding.archive.visibility = View.VISIBLE
        } else {
            binding.delete.visibility = View.GONE
        }

        binding.save.setOnClickListener {
            if (id > 0) {
                viewModel.updateTask(
                    fieldOne = binding.questionOne.text.toString(),
                    fieldTwo = binding.questionTwo.text.toString(),
                    fieldThree = binding.questionThree.text.toString(),
                    fieldFor = binding.questionFor.text.toString(),
                )
                Snackbar.make(
                    binding.root,
                    getString(R.string.update_completed),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
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
            }
            parentFragmentManager.popBackStack()
        }

        binding.delete.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                setTitle(getString(R.string.delete_ex_dialog))
                setPositiveButton(getString(R.string.yes)) {
                    viewModel.deleteEx(id)
                    parentFragmentManager.popBackStack()
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }
    }

    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.questionOne.setText(it.fieldOne)
                binding.questionTwo.setText(it.fieldTwo)
                binding.questionThree.setText(it.fieldThree)
                binding.questionFor.setText(it.fieldFor)
            }
        }
    }

    companion object {
        fun newInstance(
            id: Long = 0,
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
                putLong("ID", id)
                putIntArray("EDIT_TEXT_IDS", intArrayOf(titleOne, titleTwo, titleThree))
                putIntArray("EDIT_HINTS_IDS", intArrayOf(hintOne, hintTwo, hintThree))
                putIntArray("EDIT_INFO_IDS", intArrayOf(infoOne, infoTwo, infoThree))
                putSerializable("ENUM", typeEx)
            }
        }
    }
}
