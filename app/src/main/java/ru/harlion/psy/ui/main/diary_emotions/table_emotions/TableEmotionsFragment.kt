package ru.harlion.psy.ui.main.diary_emotions.table_emotions

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTableEmotionsBinding
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter.AdapterTableEmotions


class TableEmotionsFragment :
    BindingFragment<FragmentTableEmotionsBinding>(FragmentTableEmotionsBinding::inflate) {

    private lateinit var adapterEmo: AdapterTableEmotions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        adapterEmo = AdapterTableEmotions()
        binding.emoTableRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmo
        }
        adapterEmo.items = listOf(
            Emotion(name = "name"),
            Emotion(name = "name"),
            Emotion(name = "name"),
            Emotion(name = "name"),
            Emotion(name = "name"),
        )
    }

//    companion object {
//        fun newInstance(emotions: List<Emotion>) = TableEmotionsFragment().apply {
//            arguments = Bundle().apply {
//                putSerializable("EMOTIONS", emotions)
//            }
//        }
//    }
}