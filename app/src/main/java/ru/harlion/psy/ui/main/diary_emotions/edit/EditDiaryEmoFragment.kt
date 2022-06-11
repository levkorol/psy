package ru.harlion.psy.ui.main.diary_emotions.edit

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import ru.astrocode.flm.FlowLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditDiaryEmoBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotion
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.TableEmotionsFragment
import ru.harlion.psy.utils.dateToString
import ru.harlion.psy.utils.dialogs.DialogCalendar
import ru.harlion.psy.utils.replaceFragment
import ru.harlion.psy.utils.timeToString
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashSet


class EditDiaryEmoFragment :
    BindingFragment<FragmentEditDiaryEmoBinding>(FragmentEditDiaryEmoBinding::inflate) {

    private val viewModel: EditEmoDiaryViewModel by viewModels()
    private lateinit var adapterEmotions: AdapterEmotion
    private var date = System.currentTimeMillis()
    private var time = System.currentTimeMillis()
    private var id = 0L
    private val emotions: HashSet<Emotion> = hashSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("calendarDate") { _, bundle ->
            date = bundle.getLong("epochMillis")
            binding.date.text = dateToString(date)
        }

        setFragmentResultListener("table_emotions") { _, bundle ->
            emotions.clear()
            emotions.addAll(bundle.getSerializable("emotions") as HashSet<Emotion>)
            adapterEmotions.items = emotions.toList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        observe()
        initClicks()

        binding.date.text = dateToString(date)
        binding.time.text = timeToString(time)
        binding.questionOne.textInfo = resources.getText(R.string.thanks_diary)
        binding.questionTwo.textInfo = resources.getText(R.string.thanks_diary)
        binding.questionThree.textInfo = resources.getText(R.string.thanks_diary)
        binding.questionFor.textInfo = resources.getText(R.string.thanks_diary)
        binding.questionOne.lines = 3
        binding.questionTwo.lines = 3
        binding.questionThree.lines = 3
        binding.questionFor.lines = 3

        adapterEmotions = AdapterEmotion(emotions)
        binding.recyclerEmotions.apply {
            val llm = FlowLayoutManager(FlowLayoutManager.VERTICAL)
            layoutManager = llm
            adapter = adapterEmotions
        }
    }

    private fun observe() {
        viewModel.getEmoEventById(id)
        viewModel.emotionEvent.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.date.text = dateToString(it.date)
                binding.time.text = timeToString(it.time)
                binding.questionOne.setText(it.fieldOne)
                binding.questionTwo.setText(it.fieldTwo)
                binding.questionThree.setText(it.fieldThree)
                binding.questionFor.setText(it.fieldFor)
                adapterEmotions.items = emotions.toList()
            }
        })
    }

    private fun initClicks() {
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }
        binding.save.setOnClickListener {
            if (id > 0) {
                viewModel.update(
                    date,
                    time,
                    binding.questionOne.text.toString(),
                    binding.questionTwo.text.toString(),
                    binding.questionThree.text.toString(),
                    binding.questionFor.text.toString(),
                    emotions
                )
            } else {
                viewModel.add(
                    date,
                    time,
                    binding.questionOne.text.toString(),
                    binding.questionTwo.text.toString(),
                    binding.questionThree.text.toString(),
                    binding.questionFor.text.toString(),
                    emotions
                )
            }
            parentFragmentManager.popBackStack()
        }
        binding.btnEmotions.setOnClickListener {
            replaceFragment(TableEmotionsFragment(), true)
        }
        binding.date.setOnClickListener {
            DialogCalendar().show(parentFragmentManager, null)
        }
        binding.time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.time.text = SimpleDateFormat("HH:mm").format(cal.time)
                time = cal.time.time
            }
            TimePickerDialog(
                context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    companion object {
        fun newInstance(id: Long) = EditDiaryEmoFragment().apply {
            arguments = Bundle().apply {
                putLong("ID", id)
            }
        }
    }
}