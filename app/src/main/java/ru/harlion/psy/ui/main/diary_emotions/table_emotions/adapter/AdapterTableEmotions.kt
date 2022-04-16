package ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter

import android.view.ViewGroup
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
    }

    override fun onBindViewHolder(holder: ItemHolderTableEmotions, position: Int) {
       holder.binding.apply {
         nameEmoCategory.text = items[position].name
       }
    }

    override fun getItemCount() = items.size
}