package ru.harlion.psy.ui.main.diary_emotions.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion

private typealias ItemHolderEmotion = BindingHolder<ItemEmotionBinding>

class AdapterEmotion() : RecyclerView.Adapter<ItemHolderEmotion>() {

    var colors: ColorStateList? = null

    var items: CategoryEmotions = CategoryEmotions.emptyCategory
        set(value) {
            field = value
            colors = null
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderEmotion(ItemEmotionBinding::inflate, parent).apply {
        }

    override fun onBindViewHolder(holder: ItemHolderEmotion, position: Int) {
        val colors = colors ?: ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ), intArrayOf(
                ContextCompat.getColor(holder.itemView.context, items.color),
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray_super_light
                )
            )
        ).also { colors = it }

        holder.binding.apply {
            textEmotion.apply {

                text = items.emotions[position]
                checkbox.buttonTintList = colors

                //  setBackgroundColor(ContextCompat.getColor(this.context, item[position].color))
                // setBackgroundColor(ContextCompat.getColor(this.context, R.color.emo5))
            }
        }
    }

    override fun getItemCount() = items.emotions.size

}


