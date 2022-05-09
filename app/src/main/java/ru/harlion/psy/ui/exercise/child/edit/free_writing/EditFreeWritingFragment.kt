package ru.harlion.psy.ui.exercise.child.edit.free_writing


import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditFreeWritingBinding
import ru.harlion.psy.utils.formatTimeMinsSec
import ru.harlion.psy.utils.timeToString


class EditFreeWritingFragment :
    BindingFragment<FragmentEditFreeWritingBinding>(FragmentEditFreeWritingBinding::inflate) {

    private var onSeekBarChange: ((Int) -> Unit)? = null
    private val viewModel: EditFreeWritingViewModel by viewModels()
    private var id = 0L
    private var timer: CountDownTimer? = null
    private var timeInMilliSeconds = 60000L
    private var timeInMilli = 0L


    private var textValueSeekBar: CharSequence
        get() = binding.countTimeSeek.text
        private set(value) {
            binding.countTimeSeek.text = value
        }

    private var progressSeekBar: Int
        get() = binding.seekBar.progress
        set(value) {
            binding.seekBar.progress = value
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        viewModel.getExById(id)

        binding.fieldOne.title = resources.getText(R.string.ex_free_writing_qwestion_one)
        binding.fieldThree.lines = 20
        binding.fieldThree.title = resources.getText(R.string.free_writing_field_three)

        observe()
        initClicks()

        setProgressBarTime(progressSeekBar)
    }


    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner, {
            if (id > 0) {
                binding.stepOne.visibility = View.GONE
                binding.stepTwo.visibility = View.GONE
                binding.detailLl.visibility = View.VISIBLE

                binding.fieldOneDetail.text = it.fieldOne
                binding.timeFieldTwo.text = it.fieldTwo
                binding.fieldThreeDetaill.text = it.fieldThree
            }
        })
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
        timer = null
    }

    private fun initClicks() {
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.btnBeginEx.setOnClickListener {
            if (binding.fieldOne.text.isNotBlank()) {
                binding.stepOne.visibility = View.GONE
                binding.stepTwo.visibility = View.VISIBLE
                binding.myRequestFieldOne.text = binding.fieldOne.text
                startTimer()
            } else {
                Snackbar.make(binding.root, getString(R.string.empty), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeInMilliSeconds, 1000) {
            override fun onFinish() {
                loadKonfetti()
                viewModel.add(
                    binding.fieldOne.text.toString(),
                    formatTimeMinsSec(timeInMilli, resources),
                    binding.fieldThree.text.toString(),
                    )
                parentFragmentManager.popBackStack()
            }

            override fun onTick(p0: Long) {
                timeInMilliSeconds = p0
                updateTextUI()
            }
        }
        timer?.start()
    }

    private fun setProgressBarTime(progress: Int) {
        val seekBar = binding.seekBar
        seekBar.progress = progress
        seekBar.onProgressChange { progressChange ->
            textValueSeekBar = progressChange.toString()
            onSeekBarChange?.invoke(progressChange)

            timeInMilliSeconds = progressChange.toLong() * 60 * 1000
            timeInMilli = timeInMilliSeconds
        }
        textValueSeekBar = "$progress"
    }

    private fun loadKonfetti() {
        binding.konfetti.visibility = View.VISIBLE
        binding.konfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(5f, 9f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(-50f, binding.konfetti.width + 50f, -50f, -50f)
            .streamFor(300, 5000L)
    }

    private fun updateTextUI() {
        val minute = (timeInMilliSeconds / 1000) / 60
        val seconds = (timeInMilliSeconds / 1000) % 60

        binding.timeCount.text = "$minute:$seconds"
    }

    companion object {
        fun newInstance(id: Long) = EditFreeWritingFragment().apply {
            arguments = Bundle().apply {
                putLong("ID", id)
            }
        }
    }
}

private inline fun SeekBar.onProgressChange(crossinline onChange: (Int) -> Unit) {
    setOnSeekBarChangeListener(object : SimpleSeekBarListener() {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            onChange(progress)
        }
    })
}

private open class SimpleSeekBarListener : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}