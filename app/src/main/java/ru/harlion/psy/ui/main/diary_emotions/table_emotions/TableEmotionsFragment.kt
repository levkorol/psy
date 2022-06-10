package ru.harlion.psy.ui.main.diary_emotions.table_emotions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTableEmotionsBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter.AdapterTableEmotions
import java.io.Serializable


class TableEmotionsFragment :
    BindingFragment<FragmentTableEmotionsBinding>(FragmentTableEmotionsBinding::inflate) {

    private lateinit var adapterEmoCategory: AdapterTableEmotions
    private var emotions = hashSetOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.save.setOnClickListener {
            setFragmentResult("table_emotions", Bundle().apply {
                putSerializable(
                    "emotions",
                     emotions.toList() as Serializable
                )
            })
            parentFragmentManager.popBackStack()
        }

        adapterEmoCategory = AdapterTableEmotions (checkedItems = emotions)
        binding.emoTableRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmoCategory
        }
        adapterEmoCategory.items = listOf(
            CategoryEmotions(
                name = String(Character.toChars(0x1F604)) + "  " + getString(R.string.happy),
                R.color.emotion_happy,
                resources.getStringArray(R.array.emo_happy).toList()
            ),
            CategoryEmotions(
                name = String(Character.toChars(0x1F621)) + "  " + getString(R.string.agr),
                R.color.emotion_anger,
                resources.getStringArray(R.array.emo_anger).toList()
            ),
            CategoryEmotions(
                name = String(Character.toChars(0x1F61E)) + "  " + getString(R.string.sad),
                R.color.emotion_sad,
                resources.getStringArray(R.array.emo_sad).toList()
            ),
            CategoryEmotions(
                name = String(Character.toChars(0x1F631)) + "  " + getString(R.string.scare),
                R.color.emotion_scare,
                resources.getStringArray(R.array.emo_scare).toList()
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