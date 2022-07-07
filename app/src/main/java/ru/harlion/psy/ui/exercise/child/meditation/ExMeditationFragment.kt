package ru.harlion.psy.ui.exercise.child.meditation


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.animation.addListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentExMeditationBinding
import ru.harlion.psy.utils.custom_view.MeditationDrawable


class ExMeditationFragment :
    BindingFragment<FragmentExMeditationBinding>(FragmentExMeditationBinding::inflate) {

    private var animator: ObjectAnimator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeMedi = requireArguments().getSerializable("type_meditation")
        when (typeMedi) {
            TypeMeditation.ANTI_STRESS -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_anti_stress)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
//                binding.back.imageTintList = ColorStateList.valueOf(
//                    ContextCompat.getColor(
//                        requireContext(),
//                        R.color.meditation_anti_stress
//                    )
//                )
//                binding.info.imageTintList = ColorStateList.valueOf(
//                    ContextCompat.getColor(
//                        requireContext(),
//                        R.color.meditation_anti_stress
//                    )
//                )
                binding.titleToolbar.text = getString(R.string.mditation_anti_stress)
//                val window = (activity as AppActivity).window
//                window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.meditation_anti_stress)

            }
            TypeMeditation.GOOD_MORNING -> {
                view.background = MeditationDrawable().apply {
                    color =
                        ContextCompat.getColor(requireContext(), R.color.meditation_good_morning)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
                binding.titleToolbar.text = getString(R.string.meditation_good_morning)
            }
            TypeMeditation.GOOD_NIGHT -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_good_night)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
                binding.titleToolbar.text = getString(R.string.meditation_good_night)
            }
            TypeMeditation.RECTANGLE -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_rectangle)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
                binding.titleToolbar.text = getString(R.string.meditation_rectangle)
            }
            TypeMeditation.RELAXED -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_relax)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
                binding.titleToolbar.text = getString(R.string.meditation_relax)
            }
        }

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.play.setOnClickListener {
            val meditationDrawable = view.background as MeditationDrawable
            binding.play.visibility = View.GONE
           //todo timer binding.timer.visibility = View.GONE
            binding.stop.visibility = View.VISIBLE

            when (typeMedi) {
                TypeMeditation.ANTI_STRESS -> {
                    //todo max progress 4 - min progress 4
                    lifecycleScope.launch {
                        binding.textCommands.visibility = View.VISIBLE
                        binding.textCommands.text = getString(R.string.relaxing_and_concentrate)
                        // mini progress and duration
                        animateProgress(meditationDrawable, 2000, 0.2F, 5).await()
                        binding.textCommands.text = getString(R.string.breath)
                        binding.textCommands.text = getString(R.string.exhale)
                        binding.textCommands.visibility = View.GONE
//                delay(3000) stop
                        //set text
                        // duration * repeat = timer
                        animateProgress(meditationDrawable, 4000, 0.7F, 23).await()
                    }
                }
                TypeMeditation.GOOD_NIGHT -> {
                    //todo max progress 4 -  min progress 4 - pause 4

                    lifecycleScope.launch {
                        binding.textCommands.visibility = View.VISIBLE
                        binding.textCommands.text = getString(R.string.relaxing_and_concentrate)
                        // mini progress and duration
                        animateProgress(meditationDrawable, 2000, 0.2F, 5).await()
                        binding.textCommands.text = getString(R.string.breath)
                        binding.textCommands.text = getString(R.string.exhale)
                        binding.textCommands.visibility = View.GONE
//                delay(3000) stop
                        //set text
                        // duration * repeat = timer
                        animateProgress(meditationDrawable, 4000, 0.7F, 1).await()
                        //delay(3000)
                    }
                }
                TypeMeditation.RELAXED -> {
                    //todo max progress 4 - pause 4 - min progress 4
                    lifecycleScope.launch {
                        animateProgress(meditationDrawable, 4000, 0.7F, 23).await()
                    }
                }
                TypeMeditation.GOOD_MORNING -> {
                    //todo max progress 4 -  pause 7 - min progress 8
                    lifecycleScope.launch {
                        animateProgress(meditationDrawable, 5000, 0.7F, 23).await()
                    }
                }
                TypeMeditation.RECTANGLE -> {
                    //todo max progress 4 - pause 4 - min progress 4 - pause 4
                    lifecycleScope.launch {
                        animateProgress(meditationDrawable, 5000, 0.7F, 23).await()
                    }
                }
            }

        }

        binding.stop.setOnClickListener {
            animator?.cancel()
            animator = null
            binding.play.visibility = View.VISIBLE
           //todo timer binding.timer.visibility = View.VISIBLE
            binding.stop.visibility = View.GONE
        }
    }

    private fun animateProgress(
        meditationDrawable: MeditationDrawable,
        duration: Long,
        progress: Float,
        repeatCount: Int
    ) =
        ObjectAnimator.ofFloat(
            meditationDrawable,
            MeditationDrawable.PROGRESS,
            0F, progress
        ).apply {
            animator = this
            this.duration = duration
            repeatMode = ObjectAnimator.REVERSE
            this.repeatCount = repeatCount
            start()
        }

    private suspend fun ObjectAnimator.await() = suspendCancellableCoroutine<Unit> {
        this.addListener(onEnd = { _ ->
            it.resumeWith(Result.success(Unit))
        })
        it.invokeOnCancellation {
            this.cancel()
        }
    }

    companion object {
        fun newInstance(typeMeditation: TypeMeditation) = ExMeditationFragment().apply {
            arguments = Bundle().apply {
                putSerializable("type_meditation", typeMeditation)
            }
        }
    }
}

enum class TypeMeditation {
    ANTI_STRESS,
    GOOD_MORNING,
    GOOD_NIGHT,
    RECTANGLE,
    RELAXED
}