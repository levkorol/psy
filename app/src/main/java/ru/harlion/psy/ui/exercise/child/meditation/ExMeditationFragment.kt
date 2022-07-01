package ru.harlion.psy.ui.exercise.child.meditation


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.animation.addListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentExMeditationBinding
import ru.harlion.psy.utils.custom_view.MeditationDrawable


class ExMeditationFragment :
    BindingFragment<FragmentExMeditationBinding>(FragmentExMeditationBinding::inflate) {

    private var animator : ObjectAnimator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (requireArguments().getSerializable("type_meditation")) {
            TypeMeditation.ANTI_STRESS -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_anti_stress)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
            }
            TypeMeditation.GOOD_NIGHT -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_good_night)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
            }
            TypeMeditation.RECTANGLE -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_rectangle)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
            }
            TypeMeditation.RELAXED -> {
                view.background = MeditationDrawable().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.meditation_relax)
                    bottomPadding = (30 * resources.displayMetrics.density).toInt()
                }
            }
        }



        binding.play.setOnClickListener {
            val meditationDrawable = view.background as MeditationDrawable

            lifecycleScope.launch {
//                delay(3000)
                animateProgress(meditationDrawable, 4000, 0.5F, 23).await()
            }
        }
    }

    private fun animateProgress(
        meditationDrawable: MeditationDrawable,
        duration: Long,
        progress: Float,
        repeatCount : Int
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
    GOOD_NIGHT,
    RECTANGLE,
    RELAXED
}