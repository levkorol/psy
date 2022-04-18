package ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionsTableBinding
import ru.harlion.psy.models.emotions.Emotion

private typealias ItemHolderTableEmotions = BindingHolder<ItemEmotionsTableBinding>
class AdapterTableEmotions :
RecyclerView.Adapter<ItemHolderTableEmotions>(){

    var items = listOf<Emotion>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderTableEmotions(ItemEmotionsTableBinding::inflate, parent).apply {
                        itemView.outlineProvider = object : ViewOutlineProvider() {
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
       }
    }

    override fun getItemCount() = items.size
}