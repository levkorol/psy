package ru.harlion.psy.ui.main.diary_emotions.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemDiaryEmotionsBinding
import ru.harlion.psy.databinding.ItemEmotionBinding
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.models.emotions.EmotionEvent

private typealias ItemHolderEmotionEvent = BindingHolder<ItemDiaryEmotionsBinding>

class AdapterEmotionSEvent(
    private var adapterEmotion: AdapterEmotion? = null,
    private val clickListener: (Long) -> Unit,
) : RecyclerView.Adapter<ItemHolderEmotionEvent>() {

    var items: List<EmotionEvent> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

                ItemHolderEmotionEvent(ItemDiaryEmotionsBinding::inflate, parent).apply {
                    binding.containerLl.setOnClickListener {
                        if (adapterPosition > -1) {
                            clickListener.invoke(items[adapterPosition].id)
                        }
                    }
                }

    override fun onBindViewHolder(holder: BindingHolder<ItemDiaryEmotionsBinding>, position: Int) {
                holder.binding.apply {
                    emotionName.text = items[position].name
                     time.text = "19:00"
                    // containerItemCardView.setBackgroundColor(item[position].color)
                    emotionsRecyclerView.adapter
                    initRecyclerViewEmotion(emotionsRecyclerView, items[position].emotion)
                }
    }

    override fun getItemCount() = items.size

    private fun initRecyclerViewEmotion(recyclerView: RecyclerView, emotions: List<Emotion>) {
        adapterEmotion = AdapterEmotion()
        val llm =  FlexboxLayoutManager(recyclerView.context, FlexDirection.ROW, FlexWrap.WRAP)

        llm.alignItems = AlignItems.FLEX_START

        recyclerView.layoutManager = llm
        recyclerView.adapter = adapterEmotion

        adapterEmotion?.item = emotions
    }

}