package ru.harlion.psy.ui.main.diary_emotions


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.data.Repository
import ru.harlion.psy.databinding.FragmentDiaryEmotionBinding
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.models.emotions.EmotionEvent
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotionSEvent


class DiaryEmotionFragment :
    BindingFragment<FragmentDiaryEmotionBinding>(FragmentDiaryEmotionBinding::inflate) {

    private lateinit var adapterEmo: AdapterEmotionSEvent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterEmo = AdapterEmotionSEvent {
        }
        binding.diaryEmotionsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmo
        }
        adapterEmo.items = Repository.getEventEmo()
        if (adapterEmo.items.isNotEmpty()) {
            binding.diaryEmotionsRv.visibility = View.VISIBLE
            binding.emptyListEmotion.visibility = View.GONE
        } else {
            binding.diaryEmotionsRv.visibility = View.GONE
            binding.emptyListEmotion.visibility = View.VISIBLE
        }
    }
}