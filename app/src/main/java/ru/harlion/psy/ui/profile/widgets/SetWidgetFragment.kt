package ru.harlion.psy.ui.profile.widgets

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentSetWidgetBinding


class SetWidgetFragment : BindingFragment<FragmentSetWidgetBinding>(FragmentSetWidgetBinding::inflate) {

    private lateinit var adapterWidgets: WidgetsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        adapterWidgets = WidgetsAdapter()
        binding.widgetsRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterWidgets
        }
       adapterWidgets.items = listOf(
           ItemWidget("PsyCat", R.drawable.bg_multi_gradient),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_middle_violet),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_multi_gradient),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
           ItemWidget("Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
           ItemWidget("Psy", R.drawable.load_bg, isBlock = true),
           ItemWidget("Psy", R.drawable.load_bg, isBlock = true),
           ItemWidget("aaaa", R.drawable.load_bg, isBlock = true),
       )
    }

}