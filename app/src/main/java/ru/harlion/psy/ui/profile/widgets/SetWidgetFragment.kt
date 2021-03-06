package ru.harlion.psy.ui.profile.widgets

import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentSetWidgetBinding
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.replaceFragment


class SetWidgetFragment :
    BindingFragment<FragmentSetWidgetBinding>(FragmentSetWidgetBinding::inflate) {

    private lateinit var adapterWidgets: WidgetsAdapter
    private lateinit var prefs: Prefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.info.setOnClickListener {
            replaceFragment(
                ExInstructionsFragment.newInstance(
                    oneTitle = R.string.about_widgets,
                    toolbar = R.string.set_widget,
                    isEx = false
                ), true
            )
        }

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
                R.drawable.bg_multi_gradient_radius,
                textColor = R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.widget_bg_1,
                textColor = R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_fire,
                textColor = R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pic_bg_7,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_1,
                isBlock = true,
                textColor =  R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pic_bg_8,
                isBlock = true,
                textColor =  R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pic_bg_9,
                isBlock = true,
                textColor =  R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_4,
                isBlock = true,
                textColor =  R.color.white
            ),
            ItemWidget(
                "Здесь твоя аффирмация",
                R.drawable.pick_bg_5,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Psy - my love",
                R.drawable.pick_bg_10,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Psy - my love",
                R.drawable.pic_bg_11,
                isBlock = true,
                textColor = R.color.white
            ),
            ItemWidget(
                "Psy - my love",
                R.drawable.pick_bg_2,
                isBlock = true,
                textColor = R.color.white
            )
        )
    }
}