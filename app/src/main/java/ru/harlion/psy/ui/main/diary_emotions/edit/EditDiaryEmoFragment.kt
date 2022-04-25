package ru.harlion.psy.ui.main.diary_emotions.edit

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.data.Repository
import ru.harlion.psy.databinding.FragmentEditDiaryEmoBinding
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotion
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.TableEmotionsFragment
import ru.harlion.psy.utils.replaceFragment


class EditDiaryEmoFragment :
    BindingFragment<FragmentEditDiaryEmoBinding>(FragmentEditDiaryEmoBinding::inflate) {

    private lateinit var adapterEmotions : AdapterEmotion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()

        adapterEmotions = AdapterEmotion()
        binding.recyclerEmotions.apply {
            val llm =  FlexboxLayoutManager(requireContext(), FlexDirection.ROW, FlexWrap.WRAP)
            llm.alignItems = AlignItems.FLEX_START
            layoutManager = llm
            adapter = adapterEmotions
        }

        adapterEmotions.item = Repository.getEmo()
    }

    private fun initClicks() {
        binding.btnEmotions.setOnClickListener {
            replaceFragment(TableEmotionsFragment(), true)
        }
    }

}