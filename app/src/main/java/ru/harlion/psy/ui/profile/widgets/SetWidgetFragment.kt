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

        val allWidgets = mutableListOf<ItemWidget>()
        allWidgets.addAll(listWidgets)
        allWidgets.forEach {
            it.isBlock = false
        }
        adapterWidgets.items = if (prefs.isPremiumBilling) allWidgets else listWidgets
    }

    companion object {

        val listWidgets = listOf(
            ItemWidget(
                "Psy - my love",
                R.drawable.bg_multi_gradient,
                textColor = R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_6,
                textColor = R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_fire,
                textColor = R.color.emotion_happy
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.widget_bg_1,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_1,
                isBlock = true,
                textColor = R.color.parent_color
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_2,
                isBlock = true,
                textColor = R.color.adult_color
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_3,
                isBlock = true,
                textColor = R.color.teal_200
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_4,
                isBlock = true,
                textColor = R.color.emotion_sad
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_5,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Psy - my love",
                R.drawable.pick_bg_6,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Psy - my love",
                R.drawable.pick_bg_6,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Psy - my love",
                R.drawable.pick_bg_6,
                isBlock = true,
                textColor = R.color.white
            )
        )
    }
}