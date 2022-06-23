package ru.harlion.psy.ui.main.diary_emotions.table_emotions

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentTableEmotionsBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter.AdapterTableEmotions
import java.io.Serializable


class TableEmotionsFragment :
    BindingFragment<FragmentTableEmotionsBinding>(FragmentTableEmotionsBinding::inflate) {

    private lateinit var adapterEmoCategory: AdapterTableEmotions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments == null) {
            arguments = Bundle().apply { putSerializable("EMOTIONS", hashSetOf<Emotion>()) }
        }

        val emotions = requireArguments().getSerializable("EMOTIONS") as HashSet<Emotion>

        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.save.setOnClickListener {
            setFragmentResult("table_emotions", Bundle().apply {
                putSerializable(
                    "emotions",
                    emotions
                )
            })
            parentFragmentManager.popBackStack()
        }

        binding.countChecked.text = emotions.size.toString()

        adapterEmoCategory = AdapterTableEmotions(checkedItems = emotions)
        binding.emoTableRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEmoCategory
        }
        adapterEmoCategory.items = catalogEmo

        adapterEmoCategory.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                binding.countChecked.text = emotions.size.toString()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.countChecked.text = emotions.size.toString()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                binding.countChecked.text = emotions.size.toString()
            }
        })
    }

    companion object {
        fun newInstance(emotions: HashSet<Emotion>) = TableEmotionsFragment().apply {
            arguments = Bundle().apply {
                putSerializable("EMOTIONS", emotions)
            }
        }

        fun allEmotions(res: Resources) = catalogEmo.flatMap {
            it.getEmotions(res)
        }

        fun orderedEmotions(res: Resources, emotions: HashSet<Emotion>) = if (emotions.size < 2) {
            emotions.toList()
        } else {
            allEmotions(res).filter {
                it in emotions
            }
        }

        val catalogEmo = listOf(
            CategoryEmotions(
                0,
                0x1F604, R.string.happy,
                R.color.emotion_happy,
                R.array.emo_happy
            ),
            CategoryEmotions(
                1,
                0x1F621, R.string.agr,
                R.color.emotion_anger,
                R.array.emo_anger
            ),
            CategoryEmotions(
                2,
                0x1F61E, R.string.sad,
                R.color.emotion_sad,
                R.array.emo_sad
            ),
            CategoryEmotions(
                3,
                0x1F631, R.string.scare,
                R.color.emotion_scare,
                R.array.emo_scare
            ),
        )
    }
}