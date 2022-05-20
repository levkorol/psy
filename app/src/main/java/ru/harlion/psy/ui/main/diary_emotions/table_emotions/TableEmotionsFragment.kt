package ru.harlion.psy.ui.main.diary_emotions.table_emotions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTableEmotionsBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotion
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter.AdapterTableEmotions
import java.io.Serializable


class TableEmotionsFragment :
    BindingFragment<FragmentTableEmotionsBinding>(FragmentTableEmotionsBinding::inflate) {

    private lateinit var adapterEmoCategory: AdapterTableEmotions
    private var emotions = listOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.save.setOnClickListener {
            setFragmentResult("table_emotions", Bundle().apply {
                putSerializable(
                    "emotions", listOf(
                        Emotion(name = "Uадость"),
                        Emotion(name = "Гнев"),
                        Emotion(name = "Грусть"),
                        Emotion(name = "Стыд"),
                        Emotion(name = "Страх")
                    ) as Serializable
                ) //todo
            })
            parentFragmentManager.popBackStack()
        }

        adapterEmoCategory = AdapterTableEmotions()
        binding.emoTableRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmoCategory
        }
        adapterEmoCategory.items = listOf(
            CategoryEmotions(
                name = String(Character.toChars(0x1F604)) + "  " + "Радость",
                R.color.emotion_happy,
                resources.getStringArray(R.array.emo_happy).toList()
            ),
            CategoryEmotions(
                name = String(Character.toChars(0x1F621)) + "  " + "Гнев",
                R.color.emotion_anger,
                resources.getStringArray(R.array.emo_anger).toList()
            ),
            CategoryEmotions(
                name = String(Character.toChars(0x1F61E)) + "  " + "Грусть",
                R.color.emotion_sad,
                resources.getStringArray(R.array.emo_happy).toList()
            ),
            CategoryEmotions(
                name = String(Character.toChars(0x1F633)) + "  " + "Стыд",
                R.color.emotion_shame,
                resources.getStringArray(R.array.emo_happy).toList()
            ),
            CategoryEmotions(
                name = String(Character.toChars(0x1F631)) + "  " + "Страх",
                R.color.emotion_scare,
                resources.getStringArray(R.array.emo_happy).toList()
            ),
        )
    }

    companion object {
        fun newInstance(emotions: Serializable) = TableEmotionsFragment().apply {
            arguments = Bundle().apply {
                putSerializable("EMOTIONS", emotions)
            }
        }
    }
}