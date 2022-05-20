package ru.harlion.psy.ui.main.diary_emotions.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemDiaryEmotionsBinding
import ru.harlion.psy.databinding.ItemTextBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.models.emotions.EmotionEvent
import ru.harlion.psy.utils.dateToString
import ru.harlion.psy.utils.timeToString
import java.util.*

private typealias ItemHolderEmotionEvent = BindingHolder<ItemDiaryEmotionsBinding>

class AdapterEmotionSEvent(
    private var adapterEmotion: AdapterEmotion? = null,
    private val clickListener: (Long) -> Unit,
) : RecyclerView.Adapter<BindingHolder<*>>() {

    private val calendar = Calendar.getInstance()

    var items: List<EmotionEvent?> = listOf()
        set(value) {
            val emotionEvent = arrayListOf<EmotionEvent?>()
            var lastDate = 0L
            value.forEach {
                if (it != null) {
                    calendar.timeInMillis = it.date
                    calendar[Calendar.HOUR_OF_DAY] = 0
                    calendar[Calendar.MINUTE] = 0
                    calendar[Calendar.SECOND] = 0
                    calendar[Calendar.MILLISECOND] = 0
                    if (lastDate != calendar.timeInMillis) {
                        lastDate = calendar.timeInMillis
                        emotionEvent.add(null)
                    }
                    emotionEvent.add(it)
                }
            }
            field = emotionEvent
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] != null) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType) {
            0 -> {
                ItemHolderEmotionEvent(ItemDiaryEmotionsBinding::inflate, parent).apply {
                    binding.containerLl.setOnClickListener {
                        if (adapterPosition > -1) {
                            clickListener.invoke(items[adapterPosition]!!.id)
                        }
                    }
                }
            }
            else -> BindingHolder(ItemTextBinding::inflate, parent)
        }


    override fun onBindViewHolder(holder: BindingHolder<*>, position: Int) {
        val item = items[position]
        if(item == null) {
            holder as BindingHolder<ItemTextBinding>
            holder.binding.root.text = dateToString(items[position + 1]!!.date)

        } else {
            holder as ItemHolderEmotionEvent
            holder.binding.apply {
                emotionName.text = item.fieldOne
                time.text = timeToString(item.time)
                emotionsRecyclerView.adapter
                initRecyclerViewEmotion(emotionsRecyclerView, item.emotions)
            }
        }
    }

    override fun getItemCount() = items.size

    private fun initRecyclerViewEmotion(recyclerView: RecyclerView, emotions: List<String>) {
        adapterEmotion = AdapterEmotion()
        val llm = FlexboxLayoutManager(recyclerView.context, FlexDirection.ROW, FlexWrap.WRAP)

        llm.alignItems = AlignItems.FLEX_START

        recyclerView.layoutManager = llm
        recyclerView.adapter = adapterEmotion

        val category = CategoryEmotions("", R.color.adult_color,emotions)
        adapterEmotion?.items = category
    }

}