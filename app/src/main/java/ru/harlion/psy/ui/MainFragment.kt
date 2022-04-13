package ru.harlion.psy.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentMainBinding
import ru.harlion.psy.ui.adult.AdultExercizesFragment
import ru.harlion.psy.ui.child.ChildExercizesFragment
import ru.harlion.psy.ui.main_exercize.diary_emotions.DiaryEmotionFragment
import ru.harlion.psy.ui.main_exercize.my_day.DayPollFragment
import ru.harlion.psy.ui.parent.ParentExercizesFragment
import ru.harlion.psy.utils.replaceFragment


class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()

        binding.viewPager.adapter = ViewPager(childFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabTasks, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.diary_emo)
                }
                else -> {
                    tab.text = getString(R.string.day_star)
                }
            }
        }.attach()
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
class ViewPager(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DiaryEmotionFragment()
            else -> DayPollFragment()
        }
    }
}