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
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotion
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.TableEmotionsFragment
import ru.harlion.psy.utils.dateToString
import ru.harlion.psy.utils.dialogs.DialogCalendar
import ru.harlion.psy.utils.replaceFragment
import ru.harlion.psy.utils.timeToString
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.collections.HashSet


class EditDiaryEmoFragment :
    BindingFragment<FragmentEditDiaryEmoBinding>(FragmentEditDiaryEmoBinding::inflate) {

    private val viewModel: EditEmoDiaryViewModel by viewModels()
    private lateinit var adapterEmotions: AdapterEmotion
    private var date = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000
    private var time = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond() * 1000
    private var id = 0L
    private val emotions: HashSet<Emotion> = hashSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("calendarDate") { _, bundle ->
            date = bundle.getLong("epochMillis")
            binding.date.text = dateToString(date)
        }

        setFragmentResultListener("table_emotions") { _, bundle ->
            val newEmo = bundle.getSerializable("emotions") as HashSet<Emotion>
            if (newEmo != emotions) {
                emotions.clear()
                emotions.addAll(newEmo)
            }
            adapterEmotions.items = TableEmotionsFragment.orderedEmotions(resources, emotions)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        observe()
        initClicks()

        if (id > 0L) {
            binding.delete.visibility = View.VISIBLE
        }

        binding.date.text = dateToString(date)
        binding.time.text = timeToString(time)
        binding.questionOne.textInfo = resources.getText(R.string.emo_hint_one)
        //binding.questionTwo.textInfo = resources.getText(R.string.emo_hint_two)
        binding.questionThree.textInfo = resources.getText(R.string.emo_hint_two)
        binding.questionFor.textInfo = resources.getText(R.string.emo_hint_three)

        adapterEmotions = AdapterEmotion(emotions) {
            adapterEmotions.items = TableEmotionsFragment.orderedEmotions(resources, emotions)
        }
        binding.recyclerEmotions.apply {
            val llm = FlowLayoutManager(FlowLayoutManager.VERTICAL)
            layoutManager = llm
            adapter = adapterEmotions
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.update(
            date,
            time,
            binding.questionOne.text.toString(),
            binding.questionTwo.text.toString(),
            binding.questionThree.text.toString(),
            binding.questionFor.text.toString(),
            emotions
        )
    }

    private fun observe() {
        viewModel.getEmoEventById(id)
        viewModel.emotionEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.date.text = dateToString(it.date)
                binding.time.text = timeToString(it.time)
                binding.questionOne.setText(it.fieldOne)
                binding.questionTwo.setText(it.fieldTwo)
                binding.questionThree.setText(it.fieldThree)
                binding.questionFor.setText(it.fieldFor)

                emotions.addAll(it.emotions)
                adapterEmotions.items = TableEmotionsFragment.orderedEmotions(resources, emotions)
            }
        }
    }

    private fun initClicks() {
        binding.info.setOnClickListener {
            replaceFragment(
                ExInstructionsFragment.newInstance(
                    oneTitle = R.string.ex_info_emo_one,
                    twoTitle = R.string.ex_info_emo_two,
                    forTitle = R.string.ex_info_emo_for,
                    toolbar = R.string.info
                ), true
            )
        }
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
            replaceFragment(TableEmotionsFragment.newInstance(emotions), true)
        }
        binding.delete.setOnClickListener {
            viewModel.delete(id)
            parentFragmentManager.popBackStack()
        }
        binding.date.setOnClickListener {
            DialogCalendar().show(parentFragmentManager, null)
        }
        binding.time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.time.text =
                    SimpleDateFormat("HH:mm").format(cal.time).format(Locale.getDefault())
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