package ru.harlion.psy.ui.main.diary_emotions.adapter

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionBinding
import ru.harlion.psy.models.emotions.Emotion

private typealias ItemHolderEmotion = BindingHolder<ItemEmotionBinding>

class AdapterEmotion() : RecyclerView.Adapter<ItemHolderEmotion>() {

    var item: List<Emotion> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderEmotion(ItemEmotionBinding::inflate, parent).apply {
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

    override fun onBindViewHolder(holder: ItemHolderEmotion, position: Int) {
        holder.binding.apply {
            textEmotion.apply {
                text = item[position].name
              //  setBackgroundColor(ContextCompat.getColor(this.context, item[position].color))
              // setBackgroundColor(ContextCompat.getColor(this.context, R.color.emo5))
            }
        }
    }

    override fun getItemCount() = item.size

}


