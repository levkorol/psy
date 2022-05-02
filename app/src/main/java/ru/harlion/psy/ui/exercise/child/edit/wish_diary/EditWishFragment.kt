package ru.harlion.psy.ui.exercise.child.edit.wish_diary


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditWishBinding


class EditWishFragment :
    BindingFragment<FragmentEditWishBinding>(FragmentEditWishBinding::inflate) {

    private val viewModel: EditWishViewModel by viewModels()
    private var id = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        viewModel.getExById(id)

        binding.fieldOne.title = getString(R.string.ex_wish_field_one)
        binding.addItem.hint = getString(R.string.ex_wish_hint)

        observe()
        initClicks()
    }

    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner, {
            binding.fieldOne.setText(it.fieldOne)
            binding.addItem.items = it.listString
        })
    }

    private fun initClicks() {
        binding.save.setOnClickListener {
            if (id > 0) {
                viewModel.update(
                    binding.fieldOne.text.toString(),
                    0,
                    0,
                    binding.addItem.items.map {
                        it.toString()
                    }
                )
            } else {
                viewModel.add(
                    binding.fieldOne.text.toString(),
                    0,
                    0,
                    binding.addItem.items.map {
                        it.toString()
                    }
                )
            }
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance(id: Long) = EditWishFragment().apply {
            arguments = Bundle().apply {
                putLong("ID", id)
            }
        }
    }
}