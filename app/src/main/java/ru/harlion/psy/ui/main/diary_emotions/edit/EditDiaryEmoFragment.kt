package ru.harlion.psy.ui.main.diary_emotions.edit

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
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


class EditDiaryEmoFragment :
    BindingFragment<FragmentEditDiaryEmoBinding>(FragmentEditDiaryEmoBinding::inflate) {

    private val viewModel: EditEmoDiaryViewModel by viewModels()
    private lateinit var adapterEmotions: AdapterEmotion
    private var date = System.currentTimeMillis()
    private var time = System.currentTimeMillis()
    private var id = 0L
    private var emotions: List<Emotion> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("calendarDate") { _, bundle ->
            date = bundle.getLong("epochMillis")
            binding.date.text = dateToString(date)
        }

        setFragmentResultListener("table_emotions") { _, bundle ->
            emotions = bundle.getSerializable("emotions") as List<Emotion>
            val category = CategoryEmotions("", R.color.adult_color, emotions)
            adapterEmotions.items = category
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        observe()
        initClicks()

        binding.date.text = dateToString(date)
        binding.time.text = timeToString(time)
        binding.questionOne.hint = resources.getText(R.string.thanks_diary)
        binding.questionOne.textInfo = resources.getText(R.string.thanks_diary)
        binding.questionTwo.hint = resources.getText(R.string.thanks_diary)
        binding.questionTwo.textInfo = resources.getText(R.string.thanks_diary)
        binding.questionThree.hint = resources.getText(R.string.thanks_diary)
        binding.questionThree.textInfo = resources.getText(R.string.thanks_diary)
        binding.questionFor.hint = resources.getText(R.string.thanks_diary)
        binding.questionFor.textInfo = resources.getText(R.string.thanks_diary)

        adapterEmotions = AdapterEmotion()
        binding.recyclerEmotions.apply {
            val llm = FlexboxLayoutManager(requireContext(), FlexDirection.ROW, FlexWrap.WRAP)
            llm.alignItems = AlignItems.FLEX_START
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