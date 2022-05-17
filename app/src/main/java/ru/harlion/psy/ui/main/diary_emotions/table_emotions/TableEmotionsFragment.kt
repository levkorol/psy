package ru.harlion.psy.ui.main.diary_emotions.table_emotions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.save.setOnClickListener {
            setFragmentResult("table_emotions", Bundle().apply {
                putSerializable("emotions", listOf(
                    Emotion(name = "Uадость"),
                    Emotion(name = "Гнев"),
                    Emotion(name = "Грусть"),
                    Emotion(name = "Стыд"),
                    Emotion(name = "Страх")
                ) as Serializable) //todo
            })
            parentFragmentManager.popBackStack()
        }

        adapterEmoCategory = AdapterTableEmotions()
        binding.emoTableRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmoCategory
        }
        adapterEmoCategory.items = listOf(
            CategoryEmotions(name = "Радость", 0, listOf(Emotion(name = "emo1"), Emotion(name = "emo2"), )),//todo
            CategoryEmotions(name = "Гнев",0, listOf(Emotion(name = "emo1"), Emotion(name = "emo2"), )),
            CategoryEmotions(name = "Грусть",0, listOf(Emotion(name = "emo1"), Emotion(name = "emo2"), )),
            CategoryEmotions(name = "Стыд",0, listOf(Emotion(name = "emo1"), Emotion(name = "emo2"), )),
            CategoryEmotions(name = "Страх",0, listOf(Emotion(name = "emo1"), Emotion(name = "emo2"), )),
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