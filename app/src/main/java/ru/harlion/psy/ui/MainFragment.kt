package ru.harlion.psy.ui

import android.os.Bundle
import android.view.View
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentMainBinding
import ru.harlion.psy.ui.adult.AdultExercizesFragment
import ru.harlion.psy.ui.child.ChildExercizesFragment
import ru.harlion.psy.ui.parent.ParentExercizesFragment
import ru.harlion.psy.utils.replaceFragment


class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()
    }

    private fun initClicks() {
        binding.cardChild.setOnClickListener {
            replaceFragment(ChildExercizesFragment(), true)
        }

        binding.cardAdult.setOnClickListener {
            replaceFragment(AdultExercizesFragment(), true)
        }

        binding.cardParent.setOnClickListener {
            replaceFragment(ParentExercizesFragment(), true)
        }
    }
}