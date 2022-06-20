package ru.harlion.psy.ui.profile.widgets

import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentSetWidgetBinding
import ru.harlion.psy.utils.Prefs


class SetWidgetFragment :
    BindingFragment<FragmentSetWidgetBinding>(FragmentSetWidgetBinding::inflate) {

    private lateinit var adapterWidgets: WidgetsAdapter
    private lateinit var prefs: Prefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        adapterWidgets = WidgetsAdapter(prefs)
        binding.widgetsRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterWidgets
        }
        adapterWidgets.items = listWidgets
    }

    companion object {
        val listWidgets = listOf(
            ItemWidget( "Psy - my love", R.drawable.bg_multi_gradient),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_middle_violet),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_multi_gradient),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
            ItemWidget( "Здесь твоя аффирмация", R.drawable.bg_multi_gradient, isBlock = true),
            ItemWidget( "Psy", R.drawable.bg_multi_gradient, isBlock = true),
            ItemWidget( "Psy", R.drawable.bg_multi_gradient_radius, isBlock = true),
            ItemWidget( "Psy", R.drawable.bg_multi_gradient_radius, isBlock = true),
        )
    }
}