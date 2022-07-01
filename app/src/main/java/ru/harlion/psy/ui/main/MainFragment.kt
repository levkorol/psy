package ru.harlion.psy.ui.main

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ru.harlion.psy.R
import ru.harlion.psy.app
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentMainBinding
import ru.harlion.psy.ui.exercise.adult.AdultExercizesFragment
import ru.harlion.psy.ui.exercise.base.edit.poll_test.EditPollTestFragment
import ru.harlion.psy.ui.exercise.child.ChildExercizesFragment
import ru.harlion.psy.ui.exercise.child.meditation.ExMeditationFragment
import ru.harlion.psy.ui.exercise.child.meditation.TypeMeditation
import ru.harlion.psy.ui.main.diary_emotions.DiaryEmotionFragment
import ru.harlion.psy.ui.main.my_day.DayPollFragment
import ru.harlion.psy.ui.exercise.parent.ParentExercizesFragment
import ru.harlion.psy.ui.main.diary_emotions.edit.EditDiaryEmoFragment
import ru.harlion.psy.ui.profile.ProfileFragment
import ru.harlion.psy.ui.profile.premium.PremiumFragment
import ru.harlion.psy.ui.profile.test.TestFragment
import ru.harlion.psy.utils.*
import ru.harlion.psy.utils.dialogs.InfoDialog
import java.io.File


class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private var isRotateFab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("info_test") { _, bundle ->
            if(bundle.getBoolean("test")) {
                replaceFragment(EditPollTestFragment.newInstance(isTesting = true), true)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = Prefs(requireContext())

        isRotateFab = false

        initClicks()

        if (!pref.isShowTestBeginAppUse) {
            InfoDialog.newInstance(isTest = true).show(parentFragmentManager, null)
            pref.isShowTestBeginAppUse = true
        }

        //  Alarm.setAlarm(requireContext())

        app.user.observe(viewLifecycleOwner) {
            binding.progressAdult.progress = it.progressAdult
            binding.progressChild.progress = it.progressChild
            binding.progressParent.progress = it.progressParent

            val photoUri = Uri.fromFile(File(it.photoMain))
            try {
                binding.photo.setRoundImage(photoUri, R.drawable.ic_profile)
            } catch (e: Exception) {
                binding.photo.setRoundImage(null, R.drawable.ic_profile)
            }
        }

        binding.viewPager.adapter = ViewPager(childFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabTasks, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.diary_emo)
                }
                else -> {
                    tab.text = getString(R.string.poll_day_ex)
                }
            }
        }.attach()
    }

    private fun initClicks() {

        ViewAnimated.init(binding.fabEmoDiary)
        ViewAnimated.init(binding.fabPollDay)

        binding.cardChild.setOnClickListener {
            replaceFragment(ChildExercizesFragment(), true)
        }

        binding.cardAdult.setOnClickListener {
            replaceFragment(AdultExercizesFragment(), true)
        }

        binding.cardParent.setOnClickListener {
            replaceFragment(ParentExercizesFragment(), true)
        }
        binding.photo.setOnClickListener {
            replaceFragment(ProfileFragment(), true)
        }

        binding.addBtnMain.setOnClickListener {
            isRotateFab = ViewAnimated.rotateFab(it, !isRotateFab)
            if (isRotateFab) {
                ViewAnimated.showIn(binding.fabEmoDiary)
                ViewAnimated.showIn(binding.fabPollDay)
            } else {
                ViewAnimated.showOut(binding.fabEmoDiary)
                ViewAnimated.showOut(binding.fabPollDay)
            }
        }

        binding.fabEmoDiary.setOnClickListener {
            replaceFragment(
                EditDiaryEmoFragment.newInstance(0),
                true
            )
        }
        binding.fabPollDay.setOnClickListener {
            replaceFragment(
                EditPollTestFragment.newInstance(
                    0,
                    false
                ), true
            )
        }
        binding.test.setOnClickListener {
            replaceFragment(TestFragment.newInstance(false, floatArrayOf()), true)
        }

        binding.logo.setOnClickListener {
            replaceFragment(ExMeditationFragment.newInstance(TypeMeditation.ANTI_STRESS), true)
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