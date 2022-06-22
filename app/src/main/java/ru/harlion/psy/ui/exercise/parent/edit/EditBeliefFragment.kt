package ru.harlion.psy.ui.exercise.parent.edit


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditBeliefBinding
import ru.harlion.psy.ui.exercise.child.edit.wish_diary.EditWishFragment
import ru.harlion.psy.utils.dialogs.EditTextDialog


class EditBeliefFragment : BindingFragment<FragmentEditBeliefBinding>(FragmentEditBeliefBinding::inflate) {

    private val viewModel : EditBeliefViewModel by viewModels()
    private var id = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        viewModel.getExById(id)

        binding.questionOne.title = getString(R.string.ex_belief_one)
        binding.questionTwo.title = getString(R.string.ex_belief_two)
        binding.addItem.hint = getString(R.string.ex_belief_item_hint)

        observe()
        initClicks()
    }

    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner) {
            if (id > 0) {
                binding.questionOne.setText(it.fieldOne)
                binding.addItem.items = it.listString
                binding.questionTwo.setText(it.fieldTwo)

                binding.delete.visibility = View.VISIBLE
            }
        }
    }

    private fun initClicks() {
        binding.delete.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                setTitle(getString(R.string.delete_ex_dialog))
                setPositiveButton(getString(R.string.yes)) {
                    viewModel.delete(id)
                    parentFragmentManager.popBackStack()
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }
        binding.save.setOnClickListener {
            if (id > 0) {
                viewModel.update(
                    binding.questionOne.text.toString(),
                    binding.addItem.items.map {
                        it.toString()
                    },
                    binding.questionTwo.text.toString(),
                )
            } else {
                viewModel.add(
                    binding.questionOne.text.toString(),
                    binding.addItem.items.map {
                        it.toString()
                    },
                    binding.questionTwo.text.toString(),
                )
            }
            parentFragmentManager.popBackStack()
        }
    }


    companion object {
        fun newInstance(id: Long) = EditBeliefFragment().apply {
            arguments = Bundle().apply {
                putLong("ID", id)
            }
        }
    }
}