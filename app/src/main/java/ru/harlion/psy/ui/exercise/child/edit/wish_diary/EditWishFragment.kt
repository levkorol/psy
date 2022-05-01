package ru.harlion.psy.ui.exercise.child.edit.wish_diary


import android.os.Bundle
import android.view.View
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditWishBinding


class EditWishFragment : BindingFragment<FragmentEditWishBinding>(FragmentEditWishBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fieldOne.title = getString(R.string.ex_wish_field_one)
    }
}