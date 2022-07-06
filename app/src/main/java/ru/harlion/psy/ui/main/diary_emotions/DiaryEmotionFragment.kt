package ru.harlion.psy.ui.main.diary_emotions


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentDiaryEmotionBinding
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotionSEvent
import ru.harlion.psy.ui.main.diary_emotions.edit.EditDiaryEmoFragment
import ru.harlion.psy.utils.replaceFragment


class DiaryEmotionFragment :
    BindingFragment<FragmentDiaryEmotionBinding>(FragmentDiaryEmotionBinding::inflate) {

    private val viewModel: DiaryEmotionsViewModel by viewModels()
    private lateinit var adapterEmo: AdapterEmotionSEvent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterEmo = AdapterEmotionSEvent {
            requireParentFragment().replaceFragment(EditDiaryEmoFragment.newInstance(it), true)
        }

        binding.diaryEmotionsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmo
        }

        observe()
    }

    private fun observe() {
        viewModel.getListEmoEvents()
        viewModel.emoEvents.observe(viewLifecycleOwner) {
            adapterEmo.items = it.sortedBy { emoEvent ->
                emoEvent?.time }.reversed()
            if (adapterEmo.items.isNotEmpty()) {
                binding.diaryEmotionsRv.visibility = View.VISIBLE
                binding.emptyListEmotion.visibility = View.GONE
            } else {
                binding.diaryEmotionsRv.visibility = View.GONE
                binding.emptyListEmotion.visibility = View.VISIBLE
            }
        }
    }
}