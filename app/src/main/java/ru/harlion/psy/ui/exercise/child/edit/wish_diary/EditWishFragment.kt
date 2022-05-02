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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fieldOne.title = getString(R.string.ex_wish_field_one)
        binding.addItem.hint = getString(R.string.ex_wish_hint)

        initClicks()
    }

    private fun initClicks() {
        binding.save.setOnClickListener {
            viewModel.add(
                binding.fieldOne.text.toString(),
                0,
                0,
                binding.addItem.items.map {
                    it.toString()
                }
            )
        }
    }
}