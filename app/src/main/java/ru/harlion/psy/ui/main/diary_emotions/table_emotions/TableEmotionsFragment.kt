package ru.harlion.psy.ui.main.diary_emotions.table_emotions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTableEmotionsBinding
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter.AdapterTableEmotions
import java.io.Serializable
import java.time.ZoneId


class TableEmotionsFragment :
    BindingFragment<FragmentTableEmotionsBinding>(FragmentTableEmotionsBinding::inflate) {

    private lateinit var adapterEmo: AdapterTableEmotions

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

        adapterEmo = AdapterTableEmotions()
        binding.emoTableRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmo
        }
        adapterEmo.items = listOf(
            Emotion(name = "Радость"),
            Emotion(name = "Гнев"),
            Emotion(name = "Грусть"),
            Emotion(name = "Стыд"),
            Emotion(name = "Страх"),
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