package ru.harlion.psy.ui.profile.on_boarding


import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.FragmentOnBoardingBinding
import ru.harlion.psy.databinding.ItemOnBoardingBinding
import ru.harlion.psy.ui.main.MainFragment
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.replaceFragment

private typealias ItemHolder = BindingHolder<ItemOnBoardingBinding>
class OnBoardingFragment :
    BindingFragment<FragmentOnBoardingBinding>(FragmentOnBoardingBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gradient = GradientDrawable(
            GradientDrawable.Orientation.BR_TL, intArrayOf(
                requireContext().getColor(R.color.gr_5),
                requireContext().getColor(R.color.gr_4),
                requireContext().getColor(R.color.gr_3),
                requireContext().getColor(R.color.gr_2),
                requireContext().getColor(R.color.gr_1),
            )
        )
        view.background = gradient
        binding.nextButton.setOnClickListener {
            val next = binding.onBoardingViewPager.currentItem + 1
            if (next == binding.onBoardingViewPager.adapter!!.itemCount) {
                parentFragmentManager.popBackStack()
                val prefs = Prefs(requireContext())
                prefs.isShowOnBoarding = true
                replaceFragment(MainFragment(), false)
            } else {
                binding.onBoardingViewPager.setCurrentItem(
                    next,
                    true
                )
            }
        }

        binding.onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, offset: Float, offsetPx: Int) {
                val rv = binding.onBoardingViewPager.getChildAt(0) as RecyclerView

                val center = rv.findViewHolderForAdapterPosition(position) as ItemHolder?
                val right = rv.findViewHolderForAdapterPosition(position + 1) as ItemHolder?

                center?.binding?.titleTv?.translationX = offsetPx / 3f
                right?.binding?.titleTv?.translationX = offsetPx * (1-offset) / -3f
            }
        })

        binding.onBoardingViewPager.adapter = object : RecyclerView.Adapter<ItemHolder>() {
            private val images = intArrayOf(
                R.drawable.image_on_boarding_one,
                R.drawable.image_on_boarding_two,
                R.drawable.image_on_boarding_three,
                R.drawable.image_on_boarding_for
            )

            private val textTitle = intArrayOf(
                R.string.on_boarding_one,
                R.string.on_boarding_two,
                R.string.on_boarding_three,
                R.string.on_boarding_for
            )

//            private val textDesc = intArrayOf(
//                R.string.on_boarding_one_description,
//                R.string.on_boarding_two_description,
//                R.string.on_boarding_three_description
//            )


            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
                ItemHolder(ItemOnBoardingBinding::inflate, parent).apply {

                }


            override fun onBindViewHolder(holder: ItemHolder, position: Int) {
                holder.binding.apply {
                    imageIv.setImageResource(images[position])
                    titleTv.setText(textTitle[position])
                    // descriptionTv.setText(textDesc[position])
                }
            }

            override fun getItemCount(): Int = images.size
        }

        binding.dotsIndicator.setViewPager2(binding.onBoardingViewPager)
    }
}