package ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionsTableBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotion

private typealias ItemHolderTableEmotions = BindingHolder<ItemEmotionsTableBinding>

class AdapterTableEmotions(private var adapterEmotion: AdapterEmotion? = null) :
    RecyclerView.Adapter<ItemHolderTableEmotions>() {

    var items = listOf<CategoryEmotions>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderTableEmotions(ItemEmotionsTableBinding::inflate, parent).apply {
            binding.countEmo.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width,
                        view.height,
                        10 * view.resources.displayMetrics.density
                    )
                }
            }
            itemView.clipToOutline = true
        }

    override fun onBindViewHolder(holder: ItemHolderTableEmotions, position: Int) {
        holder.binding.apply {
            nameEmoCategory.text = items[position].name
            emotionsRv.adapter
            initRecyclerViewEmotion(emotionsRv, items[position].emotions)
        }
    }

    override fun getItemCount() = items.size


    private fun initRecyclerViewEmotion(recyclerView: RecyclerView, emotions: List<Emotion>) {
        adapterEmotion = AdapterEmotion()
        val llm = FlexboxLayoutManager(recyclerView.context, FlexDirection.ROW, FlexWrap.WRAP)

        llm.alignItems = AlignItems.FLEX_START

        recyclerView.layoutManager = llm
        recyclerView.adapter = adapterEmotion

        adapterEmotion?.items = emotions
    }

}